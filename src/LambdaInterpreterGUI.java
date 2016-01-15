import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;


/**
 * La classe <code>LambdaInterpreterGUI</code> a pour but de créer la Frame qui consistue le programme
 * support de l'interpréteur.
 * Le programme à pour volonté d'être une véritable mini-IDE avec son historique de commandes,
 * la sauvegarde des termes, les facilités de typage des termes, l'import/export de termes en fichier texte,
 * et la possibilité de visionner l'AST d'un terme bien formé.
 */
public class LambdaInterpreterGUI extends javax.swing.JFrame {


    private javax.swing.JFileChooser fileChoose;
    private javax.swing.JTextField inputField;
    private javax.swing.JLabel inputIcon;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem menuClearList;
    private javax.swing.JMenuItem menuExport;
    private javax.swing.JMenuItem menuHelp;
    private javax.swing.JMenuItem menuImport;
    private javax.swing.JMenuItem menuViewAST;
    private javax.swing.JCheckBox saveTermOption;
    private javax.swing.JList<String> termSavedList;
    private javax.swing.JTextArea workSpace;
    private DefaultListModel<String> saveTermModel;
    private InputHistory history;
    private String helpString;

    /**
     * Creates new LambdaInterpreterGUI
     */
    public LambdaInterpreterGUI() {
        initComponents();
    }


    /**
     * Une action qui permet de naviguer dans l'historique
     */
    class GetPrevInput extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            LambdaInterpreterGUI.this.inputField.setText(history.getPrevInput());
        }
    }

    /**
     * Une action qui permet de naviguer dans l'historique
     */
    class GetNextInput extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            LambdaInterpreterGUI.this.inputField.setText(history.getNextInput());
        }
    }

    /**
     * L'action qui permet d'écrire un lambda lors d'un appui sur la touche ";".
     */
    public class ReplaceAction extends AbstractAction {

        JTextComponent textComponent = null;

        public ReplaceAction(JTextComponent textComponent) {
            this.textComponent = textComponent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.textComponent.replaceSelection("λ");
        }
    }


    @SuppressWarnings("unchecked")

    private void initComponents() {

        fileChoose = new JFileChooser();
        inputField = new JTextField();
        history = new InputHistory();
        jScrollPane1 = new JScrollPane();
        termSavedList = new JList<>();
        jScrollPane2 = new JScrollPane();
        workSpace = new JTextArea();
        inputIcon = new JLabel();
        saveTermOption = new JCheckBox();
        jMenuBar1 = new JMenuBar();
        jMenu2 = new JMenu();
        menuImport = new JMenuItem();
        menuExport = new JMenuItem();
        menuClearList = new JMenuItem();
        jMenu1 = new JMenu();
        menuViewAST = new JMenuItem();
        jMenu3 = new JMenu();
        menuHelp = new JMenuItem();
        helpString = "Bienvenue sur le Lambda Interpreter du groupe Aubry Dubois Poivey Schersach :) \n" +
                "Cet espace de travail permet de réduire des termes du Lambda Calcul \n" +
                "décrits par la grammaire suivante : \n" +
                "M ::= c                     (constante entière) \n" +
                "         x                     (variable) \n" +
                "         M (+ - *) M      (calcul) \n" +
                "         if(M = > < <= >= != M)then{M}else{M} (condition) \n" +
                "         (λx.M)           (abstraction) \n" +
                "         M M              (application) \n" +
                "         (rec f x.M)     (recursion) \n" +
                "L'expression de la régle de récursion doit être un if pour que la fonction s'applique .\n" +
                "Le λ peut être obtenu par une pression sur la touche ';' . \n" +
                "Le λ peut aussi être remplacé par un \\ .\n" +
                "Voici quelque exemples de termes bien construits : \n" +
                "  - (λy.λx.y) 5+5 \n" +
                "  - (rec f x.if(x=0)then{1}else{x*(f x-1)}) 12 \n" +
                "  - if(4!=5)then{M}else{K} \n" +
                "Il est nécessaire de parenthéser une abstraction comme dans l'exemple (λy.λx.y) 5+5 ,\n" +
                "afin d'avoir des résultats consistants. \n" +
                "Il est possible de naviguer dans l'historique des termes via les touches UP et DOWN, \n" +
                "lorsque l'input à le focus. \n" +
                "Il est possible de revoir cette aide à tout moment : \n" +
                "Dans le menu : Séléctionnez Help.";

        fileChoose.setFileFilter(new FileNameExtensionFilter("Text file", "txt"));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lambda Interpreter");

        inputField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                inputFieldKeyPressed(evt);
            }
        });


        // Ici on remplace l'action des touches : "; UP et DOWN"
        inputField.getInputMap().put(KeyStroke.getKeyStroke(';'), "putlambda");
        inputField.getActionMap().put("putlambda", new ReplaceAction(inputField));
        inputField.getInputMap().put(KeyStroke.getKeyStroke("UP"), "getPrevious");
        inputField.getActionMap().put("getPrevious", new LambdaInterpreterGUI.GetPrevInput());
        inputField.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "getNext");
        inputField.getActionMap().put("getNext", new LambdaInterpreterGUI.GetNextInput());


        saveTermModel = new DefaultListModel<>();


        termSavedList.setModel(saveTermModel);
        termSavedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        termSavedList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                termSavedListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(termSavedList);

        jScrollPane2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        workSpace.setEditable(false);
        workSpace.setColumns(20);
        workSpace.setRows(5);
        workSpace.setText(helpString);
        jScrollPane2.setViewportView(workSpace);

        inputIcon.setIcon(new ImageIcon(getClass().getResource("logo.png")));
        inputIcon.setLabelFor(inputField);

        saveTermOption.setText("Save Term");
        saveTermOption.setSelected(true);

        jMenu2.setText("File");

        menuImport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuImport.setText("Import");
        menuImport.addActionListener(this::menuImportActionPerformed);
        jMenu2.add(menuImport);

        menuExport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuExport.setText("Export");
        menuExport.addActionListener(this::menuExportActionPerformed);
        jMenu2.add(menuExport);

        menuClearList.setText("Clear Saved Terms");
        menuClearList.addActionListener(this::menuClearListActionPerformed);
        jMenu2.add(menuClearList);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("View");


        menuViewAST.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuViewAST.setText("View AST Of Selection");
        menuViewAST.setActionCommand("View AST of Term");
        menuViewAST.addActionListener(this::menuViewASTActionPerformed);
        jMenu1.add(menuViewAST);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Help");

        menuHelp.setText("Help");
        menuHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuHelp.addActionListener(this::menuHelpActionPerformed);
        jMenu3.add(menuHelp);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(inputIcon, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(inputField)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(saveTermOption)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                .addComponent(inputField)
                                                                .addComponent(saveTermOption))
                                                        .addComponent(inputIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }

    /**
     * Cette méthode s'occupe d'interpréter les termes entrés par l'utilisateur.
     * Chaque terme entré est aussi ajouté à l'historique des commandes.
     * Pour chaque terme, si il est mal formé une erreur s'affiche.
     * Si le terme est bien formé et que la checkbox de sauvegarde est coché, le terme est sauvegardé.
     * Le terme est ensuite passé à la moulinette Lexer/Parser/Visitor autant de fois que nécessaire
     * On termine l'évaluation si le terme renvoyé est :
     * - une <code>Abstraction</code>,
     * - une <code>Variable</code>
     * - un <code>Integer</code>
     * - si le compteur d'itération est supérieur à 100
     * Durant l'évaluation les étapes intermédiaires sont souvent écrites dans le workSpace afin de
     * faciliter la compréhension des termes et de leur réduction.
     *
     * @param evt L'event qui a trigger l'action.
     */
    private void inputFieldKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            workSpace.setText("");
            String term = inputField.getText();
            history.addInput(term);
            if (term.equals("clear")) {
                workSpace.setText("");
            } else {
                Value result;
                int iterateCounter = 0;

                workSpace.append(term + "\n");
                if (saveTermOption.isSelected() && !saveTermModel.contains(term)) {
                    saveTermModel.addElement(term);
                }

                try {
                    do {
                        ParseTree tree = parse(term);
                        FreeVariableVisitor freeVariableVisitor = new FreeVariableVisitor();
                        freeVariableVisitor.visit(tree);
                        ReduceVisitor visitor = new ReduceVisitor(freeVariableVisitor.getFreeVariables());
                        result = visitor.visit(tree);
                        if (!result.asString().equals(term)) {
                            workSpace.append("  =  " + result.asString() + "\n");
                        }
                        term = result.asString();
                        iterateCounter++;
                    } while (result.checkContinue() && iterateCounter < 100);
                } catch (ParseCancellationException e) {
                    workSpace.append(e.getMessage());
                    saveTermModel.removeElement(saveTermModel.lastElement());
                } catch (ClassCastException e) {
                    workSpace.append("Please put an If Statement in the recursive function expression if you want to do \n" +
                            "an application on it.");
                }

                if (iterateCounter == 100) {
                    workSpace.append("Infinite term or not reducible :( \n");
                    workSpace.append("  =  " + term);
                }
            }

            inputField.setText("");
        }
    }

    /**
     * Simple méthode pour importer un fichier texte, dans l'espace de sauvegarde des termes.
     * Le fichier est lu ligne par ligne.
     *
     * @param evt L'event qui a trigger l'action.
     */
    private void menuImportActionPerformed(java.awt.event.ActionEvent evt) {
        int returnVal = fileChoose.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChoose.getSelectedFile();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (!saveTermModel.contains(line)) {
                        saveTermModel.addElement(line);
                    }
                }
                bufferedReader.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Problem accessing file" + file.getAbsolutePath(), "File Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "File access cancelled by user", "No File Selected", JOptionPane.ERROR_MESSAGE);
        }


    }

    /**
     * Quand un terme sauvegardé dans la liste des termes sauvegardés est séléectionné il est possible
     * de voir son AST via le menu, ou le raccourci Ctrl+I / Cmd+I
     *
     * @param evt L'event qui a trigger l'action.
     */
    private void menuViewASTActionPerformed(java.awt.event.ActionEvent evt) {
        if (!termSavedList.isSelectionEmpty()) {
            int index = termSavedList.getSelectedIndex();
            String term = saveTermModel.getElementAt(index);
            try {
                ANTLRInputStream inputStream = new ANTLRInputStream(term);
                LambdaLexer lexer = new LambdaLexer(inputStream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                LambdaParser parser = new LambdaParser(tokens);
                ParseTree tree = parse(term);
                TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
                viewer.open();
            } catch (ParseCancellationException e) {
                workSpace.append("Don't try to watch AST of illformed term please");
            }
        }
    }

    /**
     * Méthode pour revoir la mini aide / introduction
     *
     * @param evt L'event qui a trigger l'action.
     */
    private void menuHelpActionPerformed(java.awt.event.ActionEvent evt) {
        workSpace.setText(helpString);
    }

    /**
     * Méthode pour vider la liste des termes sauvegardés
     *
     * @param evt L'event qui a trigger l'action.
     */
    private void menuClearListActionPerformed(java.awt.event.ActionEvent evt) {
        saveTermModel.clear();
    }

    /**
     * Méthode pour exporter l'ensemble des termes sauvegardés dans un fichier texte.
     * Chaque terme est inscrit ligne par ligne.
     *
     * @param evt L'event qui a trigger l'action.
     */
    private void menuExportActionPerformed(java.awt.event.ActionEvent evt) {
        java.util.List<String> saveList = Collections.list(saveTermModel.elements());
        int returnVal = fileChoose.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChoose.getSelectedFile();
            Path path = Paths.get(file.getAbsolutePath());
            try {
                Files.write(path, saveList, Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No File Chose", "No File Selected", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Lors d'un double click sur un element de la liste des termes sauvegardés,
     * le terme selectionné est copié vers l'inputField.
     *
     * @param evt L'event qui a trigger l'action.
     */
    private void termSavedListMouseClicked(java.awt.event.MouseEvent evt) {
        JList list = (JList) evt.getSource();
        if (evt.getClickCount() == 2 && !saveTermModel.isEmpty()) {
            int index = list.getSelectedIndex();
            String term = saveTermModel.getElementAt(index);
            inputField.setText(term);
        }
    }

    /**
     * Cette méthode est un simple helper qui passe le String en paramètre
     * dans le Lexer/Parser. Le bon <code>ErrorListener</code> est aussi attaché
     * au lexer/parser.
     *
     * @param term Le string qui va être passé au lexer/parser.
     * @return Return l'AST de l'input.
     */
    private ParseTree parse(String term) {

        ANTLRInputStream inputStream = new ANTLRInputStream(term);

        LambdaLexer lexer = new LambdaLexer(inputStream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(LambdaErrorListener.INSTANCE);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        LambdaParser parser = new LambdaParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(LambdaErrorListener.INSTANCE);

        return parser.expression();

    }
}
