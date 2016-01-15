import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

/**
 * La classe <code>LambdaErrorListener</code> a pour de remplacer
 * Les Errors Listeners de base de ANTLR4.
 * Le but final étant de reporter les erreurs sur l'interface graphique
 * mais aussi de ne pas essayer de "recover" les erreurs dans l'input.
 * En effet ANTLR4 de base tentera toujours de transformer un input malformé
 * Cette fonctionnalité ne nous intéressant pas, on peut l'outrepasser
 * En renvoyant des <code>ParseCancellationException</code>
 */
public class LambdaErrorListener extends BaseErrorListener {

    /**
     * La constante INSTANCE renvoie une instance de <code>LambdaErrorListenr</code>.
     */
    public static final LambdaErrorListener INSTANCE = new LambdaErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
            throws ParseCancellationException {
        throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
        // Les messages sont assez précis pour permettre à l'utilisateur de corriger son input.
    }
}
