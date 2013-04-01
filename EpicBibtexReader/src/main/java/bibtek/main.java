package bibtek;

public class main {
 
    public static void main(String Args[]) {
        //Dummyyyy !
        String[][] blah = {{"article"},
            {"Whittington, Keith J."},
            {"Infusing active learning into introductory programming courses"},
            {"J. Comput. Small Coll."},
            {"19"},
            {"5"},
            {"2004"},
            {"249--259"},
            {"Consortium for Computing Sciences in Colleges"},
            {"USA"}};
        bibtex bt = new bibtex(blah);
        System.out.println(bt.toString());
        System.out.println("--------");
        System.out.println(bt.tulostaSelkokielellä());
        
    }
}