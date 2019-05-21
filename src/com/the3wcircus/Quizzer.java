package com.the3wcircus;

public class Quizzer
{
    // List to hold the questions for the Quiz
    private java.util.ArrayList<com.the3wcircus.Question> questions = new java.util.ArrayList<Question>(5);
    private java.util.ArrayList<com.the3wcircus.Question> quizQuestions = new java.util.ArrayList<Question>();

    private static final java.util.Random gen = new java.util.Random();
    private final String QUESTION_FILE = "src/com/the3wcircus/quizzer.txt";
    private String testerName;

    public Quizzer()
    {
        // Load up all the questions from the file
        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.print("Tester, enter your name Please: ");
        testerName = input.nextLine();
        try
        {
            input = new java.util.Scanner(new java.io.File(QUESTION_FILE));
            while (input.hasNextLine())
            {
                try
                {
                    String q = input.nextLine().trim();
                    if (!q.isEmpty())
                    {
//                        System.out.println(q);
                        questions.add(new com.the3wcircus.Question(q));
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            // Get a random selection of questions
            int[] questionIndexes = getRandomQuestionNumbers(5, 0, 5);
//            System.out.println(java.util.Arrays.toString(questionIndexes));


            for (int ndx : questionIndexes) {
                quizQuestions.add(questions.get(ndx));
            }

            int qk = 0;
            int correct = 0;
            for (Question q : quizQuestions) {
                qk++;
                if (q.ask(qk))
                {
                    correct++;
                }

            }
            System.out.println(String.format("%s, you correctly answered %d out of %d questions",testerName,correct,qk));
        }
        catch (java.io.FileNotFoundException e)
        {
            e.printStackTrace();
        }


    }


    private static int[] getRandomQuestionNumbers(int n, int min, int maxRange)
    {
        assert n <= maxRange : "cannot get more unique numbers than the size of the range";

        int[] result = new int[n];
        java.util.Set<Integer> used = new java.util.HashSet<Integer>();

        for (int i = 0; i < n; i++)
        {

            int newRandom;
            do
            {
                newRandom = gen.nextInt(((maxRange - min) + 1) + min);
            }
            while (used.contains(newRandom));
            result[i] = newRandom;
            used.add(newRandom);
        }
        return result;
    }
}

class Question
{

    private String questionText;
    private java.util.HashMap<Integer, String> answers = new java.util.HashMap<>();
    private int correctAnswer;
    private final String CRLF = "\n";

    Question(String questionRecord) throws Exception
    {
        // Parse question record into a Question object
        String[] questionParts = questionRecord.split(",");
        if (questionParts.length != 6)
        {
            String msg = String.format("Poorly formed question! %s", questionRecord);
            System.out.println(msg);
            throw new Exception(msg);
        }
        else
        {
            this.questionText = questionParts[0];
            this.answers.put(1, questionParts[1]);
            this.answers.put(2, questionParts[2]);
            this.answers.put(3, questionParts[3]);
            this.answers.put(4, questionParts[4]);
            this.correctAnswer = Integer.parseInt(questionParts[5]);
        }
    }

    public boolean ask(int questionKount)
    {
        boolean correct = false;
        java.util.Scanner reader = new java.util.Scanner(System.in);
        // Build question
        StringBuilder question = new StringBuilder();
        question.append(String.format("%d. %s?", questionKount, questionText)).append(CRLF).append(CRLF)
                .append(String.format("A. %s", answers.get(1))).append(CRLF)
                .append(String.format("B. %s", answers.get(2))).append(CRLF)
                .append(String.format("C. %s", answers.get(3))).append(CRLF)
                .append(String.format("D. %s", answers.get(4))).append(CRLF).append(CRLF)
                .append("Enter your answer:");

        System.out.print(question.toString());
        String answer = reader.nextLine().trim().toUpperCase();
        System.out.println("User answered "+answer+CRLF+"-----------------------------------"+CRLF);

        // Check if they got it right
        switch (answer)
        {
            case "A":
                correct = this.correctAnswer == 1;
                break;
            case "B":
                correct = this.correctAnswer == 2;
                break;
            case "C":
                correct = this.correctAnswer == 3;
                break;
            case "D":
                correct = this.correctAnswer == 4;
                break;
            default:
                correct = false;
        }
        return correct;
    }

}
