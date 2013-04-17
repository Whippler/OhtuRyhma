import bibtek.*
import bibtek.io.*
import bibtek.domain.*

description 'User can create a new reference and save it'

scenario "User leaves program without creating a reference", {
    given 'User starts executing program', {
		io = new StubIO("n", "")
		bib = new Bibtex(io)
    }

    when 'User enters necessary commands to stop executing', {
		bib.run()
    }

    then 'Program stops executing without doing anything', {
		io.getPrints().shouldHave("Bye!")
    }
}


scenario "User creates a reference and reads it in plaintext", {
	given 'User starts executing program', {
		io = new StubIO("n", "create", "article", "author", "Väinämöinen", "title", "Kalevala", "year", "1888", "", "plain", "")
		bib = new Bibtex(io)
	}

	when 'User enters the reference', {
		bib.run()
	}

	then 'User can view the reference in bibtex', {
		io.getPrints().shouldHave("author: Väinämöinen\n"
								+ "title: Kalevala\n"
								+ "year: 1888")
	}
}

scenario "User creates a reference and reads it in bibtex", {
	given 'User starts executing program', {
		io = new StubIO("n", "create", "article", "author", "Väinämöinen", "title", "Kalevala", "year", "1888", "", "bib", "")
		bib = new Bibtex(io)
	}

	when 'User enters the reference', {
		bib.run()
	}

	then 'User can view the reference in bibtex', {
		io.getPrints().shouldHave("@article{V88,\n"
								+ "author = {V\\\"{a}in\\\"{a}m\\\"{o}inen},\n"
								+ "title = {Kalevala},\n"
								+ "year = {1888},\n"
								+ "}\n")
	}
}

scenario "User creates an erroneus reference", {
	given 'User starts executing program', {
		io = new StubIO("n", "create", "article", "author", "Väinämöinen", "year", "1888", "", "")
		bib = new Bibtex(io)
	}

	when 'User enters the reference', {
		bib.run()
	}

	then 'User gets the error message', {
		io.getPrints().shouldHave("Error in creating bibtex reference!\n"
								+ "Remember that the required fields are: author, year and title")
	}
}


scenario "User creates an erroneus reference and tries to view it in plaintext", {
	given 'User starts executing program', {
		io = new StubIO("n", "create", "article", "author", "Väinämöinen", "year", "1888", "", "plain", "")
		bib = new Bibtex(io)
	}

	when 'User enters the reference', {
		bib.run()
	}

	then 'User gets the error message', {
		io.getPrints().shouldHave("No current reference to view!")
	}
}

scenario "User creates an erroneus reference and tries to view it in bibtex", {
	given 'User starts executing program', {
		io = new StubIO("n", "create", "article", "author", "Väinämöinen", "year", "1888", "", "bib", "")
		bib = new Bibtex(io)
	}

	when 'User enters the reference', {
		bib.run()
	}

	then 'User gets the error message', {
		io.getPrints().shouldHave("No current reference to view!")
	}
}

scenario "User creates an erroneus reference and tries to save it", {
	given 'User starts executing program', {
		io = new StubIO("n", "create", "article", "author", "Väinämöinen", "year", "1888", "", "save", "")
		bib = new Bibtex(io)
	}

	when 'User enters the reference', {
		bib.run()
	}

	then 'User gets the error message', {
		io.getPrints().shouldHave("No current reference to save!")
	}
}

scenario "User decides to change the file in which references are stored", {
	given 'User starts executing program', {
		io = new StubIO("y", "asd", "")
		bib = new Bibtex(io)
	}

	when 'User enters the new filename', {
		bib.run()
	}

	then 'User receives the new filename', {
		io.getPrints().shouldHave("References will now be saved into asd.bib")
	}
}






