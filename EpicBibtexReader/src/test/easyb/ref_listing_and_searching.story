import bibtek.*
import bibtek.io.*
import bibtek.domain.*

description 'User can list references from the file and search for certain references'

scenario "User wants to list references in the file", {
    given 'User starts executing program', {
			io = new StubIO("n", "list", "")
			bib = new Bibtex(io)
    }

    when 'User enters necessary commands to list references', {
			bib.run();
    }

    then 'Program lists information about the references in the file', {
			io.getPrints().shouldHave("VV88: Mies ja norppa - Vainio, Juha and Vanhapoika, Nestori\n"
															+ "M99: Kuhnurin elämää - Mehiläinen, Matti\n")
    }
}

scenario "User wants to list references in the file as bibtex", {
    given 'User starts executing program', {
			io = new StubIO("n", "biblist", "")
			bib = new Bibtex(io)
    }

    when 'User enters necessary commands to list references', {
			bib.run();
    }

    then 'Program lists information in bibtex about the references in the file', {
			io.getPrints().shouldHave("@article{VV88,\n"
                + "author = {Vainio, Juha and Vanhapoika, Nestori},\n"
                + "title = {Mies ja norppa},\n"
                + "year = {1988},\n"
                + "}\n\n\n"
                + "@book{M99,\n"
                + "author = {Mehil\\\"{a}inen, Matti},\n"
                + "title = {Kuhnurin el\\\"{a}m\\\"{a}\\\"{a}},\n"
                + "year = {1999},\n"
                + "}\n\n\n")
    }
}

scenario "User wants to select a reference", {
    given 'User starts executing program', {
			io = new StubIO("n", "select", "V8", "select", "VV88")
			bib = new Bibtex(io)
    }

    when 'User enters accidentally false id and then the correct one', {
			bib.run();
    }

    then 'Program informs the user after a correct selection', {
			io.getPrints().shouldHave("Current ref: Mies ja norppa")
    }
}

scenario "User search for a non-existent reference", {
    given 'User starts executing program', {
			io = new StubIO("n", "search", "", "plom", "")
			bib = new Bibtex(io)
    }

    when 'User enters the search term', {
			bib.run();
    }

    then 'Program informs the user if a match was found', {
			io.getPrints().shouldHave("No matches found!")
    }
}

scenario "User searches for an existing reference", {
    given 'User starts executing program', {
			io = new StubIO("n", "search","", "norppa", "")
			bib = new Bibtex(io)
    }

    when 'User enters the search term', {
			bib.run();
    }

    then 'Program displays the references consistent to the search term', {
			io.getPrints().shouldHave("1. Author: Vainio, Juha and Vanhapoika, Nestori\n"
						+ "Title: Mies ja norppa\n")
    }
}

scenario "User selects a reference and then views it in plaintext", {
    given 'User starts executing program', {
			io = new StubIO("n", "select", "VV88", "plain", "")
			bib = new Bibtex(io)
    }

    when 'User enters the required commands', {
			bib.run();
    }

    then 'Program displays the selected reference in plaintext', {
			io.getPrints().shouldHave("author: Vainio, Juha and Vanhapoika, Nestori\n"
															+ "title: Mies ja norppa\n"
															+ "year: 1988\n")
    }
}

scenario "User selects a reference and then views it in bibtex", {
    given 'User starts executing program', {
			io = new StubIO("n", "select", "VV88", "bib", "")
			bib = new Bibtex(io)
    }

    when 'User enters the required commands', {
			bib.run();
    }

    then 'Program displays the selected reference in bibtex', {
			io.getPrints().shouldHave("@article{VV88,\n"
                + "author = {Vainio, Juha and Vanhapoika, Nestori},\n"
                + "title = {Mies ja norppa},\n"
                + "year = {1988},\n"
                + "}\n\n")
    }
}

scenario "User searches for several references with the search term", {
    given 'User starts executing program', {
			io = new StubIO("n", "search", "", "19", "")
			bib = new Bibtex(io)
    }

    when 'User enters the search term', {
			bib.run();
    }

    then 'Program displays the references consistent to the search term', {
		io.getPrints().shouldHave("1. Author: Vainio, Juha and Vanhapoika, Nestori\n"
									+ "Title: Mies ja norppa\n")	
                io.getPrints().shouldHave("2. Author: Mehiläinen, Matti\n"
									+ "Title: Kuhnurin elämää\n")
									
			
									
    }
}





