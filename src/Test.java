/**
 * Test class
 */
public class Test {

    public static void main(String[] args) {
        /*String testString = "(\\x.\\y.y) (\\z.z) M";
        String testString2 = "(\\x.x x) (\\x.x x)";
        String testString3 = "(\\x.x y x) (\\x.x)";
        String testString4 = "(\\y.\\x.y) x";
        String testString5 = "(\\x.\\y.x y) (\\z.z) 5";
        String testString6 = "(\\x.x) (\\z.z)";
        String testString7 = "(\\x.x) (z k)";
        String testString8 = "(\\y.\\x.y) (\\x.x)";
        String testString9 = "(\\x.\\y.y x) (\\z.z)";
        String testString10 = "(\\x.\\y.\\z.x) (\\x.x) 5 5";
        String testString11 = "(\\x.\\y.\\z.x y)";
        String testString12 = "(λx.l y x) (λz.z) (λk.k) (λt.t)";
        String testString13 = "(λx.x x) (λx.x x)";
        String testString15 = "(rec f x.if(x=0)then{0}else{3+(f x-1)}) 7";
        String testString16 = "if(((\\x.x) 5)=5)then(if(5=4)then(4)else(\\x.x))else(K) x";
        String testString17 = "if(x=5)then(if(5=4)then(4)else(\\x.x))else(K) x";
        String newString = "";
        int iterateCounter = 0;
        Value result;
        long start = System.currentTimeMillis();

        try {
            do {
                System.out.println("new iteration");
                ANTLRInputStream inputStream = new ANTLRInputStream(testString15);
                LambdaLexer lexer = new LambdaLexer(inputStream);
                lexer.removeErrorListeners();
                lexer.addErrorListener(LambdaErrorListener.INSTANCE);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                LambdaParser parser = new LambdaParser(tokens);
                parser.removeErrorListeners();
                parser.addErrorListener(LambdaErrorListener.INSTANCE);
                ParseTree tree = parser.expression();
                FreeVariableVisitor freeVariableVisitor = new FreeVariableVisitor();
                freeVariableVisitor.visit(tree);
                ReduceVisitor visitor = new ReduceVisitor(freeVariableVisitor.getFreeVariables());
                result = visitor.visit(tree);
                testString15 = result.asString();
                iterateCounter++;
                System.out.println(result);
                System.out.println(result.value.getClass());
            } while (result.checkContinue() && iterateCounter < 100);
        } catch (ParseCancellationException e) {
            System.out.println(e.getMessage());
        }

        long stop = System.currentTimeMillis();

        long time = stop - start;

        System.out.println("Execution time = " + time);*/


        System.out.println("x+x+xy".replaceAll("\\bx\\b","5"));

    }
}
