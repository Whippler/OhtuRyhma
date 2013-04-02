package bibtek;

public class Main {

    public static void main(String Args[]) {
        //Dummyyyy !
        String[] blah = {"article",
            "Whittington, KeithööÄPÅöÄ J.",
            "Infusing active learning into introductory programming courses",
            "J. Comput. Small Coll.",
            "19",
            "5",
            "2004",
            "249--259",
            "Consortium for Computing Sciences in Colleges",
            "USA"};
        Bibtex bt = new Bibtex(blah);
         System.out.println(bt.toString());
         System.out.println("--------");
         System.out.println(bt.tulostaSelkokielellä());
         bt.tallennaTiedot();
         bt.lueKayttajanSyotteet();
         bt.tallennaTiedot();
         System.out.println("--------");
         System.out.println(bt.toString());
         System.out.println("--------");
         System.out.println(bt.tulostaSelkokielellä());

    }
}