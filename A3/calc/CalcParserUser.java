
class CalcParserUser {

    public static void main(String[] args) {
        try {
            CalcParser parser = new CalcParser(new CalcScanner(System.in));
            Integer result = (Integer) parser.parse().value;
            System.out.println("result is " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
