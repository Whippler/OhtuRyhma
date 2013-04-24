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


scenario "User tries to save nonexistent reference", {
	given 'User starts executing program', {
		io = new StubIO("y", "asd", "save", "")
		bib = new Bibtex(io)
	}

	when 'User enters the commands', {
		bib.run()
	}

	then 'User receives the error message', {
		io.getPrints().shouldHave("No current references to save!")
	}
}

scenario "User tries to read nonexistent reference in plaintext", {
	given 'User starts executing program', {

		io = new StubIO("y", "asd", "plain", "")
		bib = new Bibtex(io)
	}

	when 'User enters the commands', {
		bib.run()
	}

	then 'User receives the error message', {
		io.getPrints().shouldHave("No current reference to view!\n")
	}
}

scenario "User tries to read nonexistent reference in bibtex", {
	given 'User starts executing program', {

		io = new StubIO("y", "", "asd", "bib", "")
		bib = new Bibtex(io)
	}

	when 'User enters the commands', {
		bib.run()
	}

	then 'User receives the error message', {
		io.getPrints().shouldHave("No current reference to view!\n")
	}
}

scenario "User tries to create erroneous reference", {
	given 'User starts executing program', {

		io = new StubIO("n", "create", "article", "author", "Koira, Kille", "year", "1999", "", "title", "Haukku", "")
		bib = new Bibtex(io)
	}

	when 'User enters the commands', {
		bib.run()
	}

	then 'User receives the error message', {
		io.getPrints().shouldHave("Error! Author, Year and Title are required")
	}
}

scenario "User tries to create reference with erroneous field values", {
	given 'User starts executing program', {
		io = new StubIO("n", "create", "article", "author", "Koira, Kille", "year", "1999", "title", "Haukku", "phdteesi", "salala", "")
		bib = new Bibtex(io)
	}

	when 'User enters the commands', {
		bib.run()
	}

	then 'User is informed about the false input', {
		io.getPrints().shouldHave("Invalid input! Check that the field is written correctly and the content is not empty.")
	}
}


scenario "User tries to create reference with erroneous type", {
	given 'User starts executing program', {
		io = new StubIO("n", "create", "artikle", "article", "author", "Koira, Kille", "year", "1999", "title", "Haukku", "")
		bib = new Bibtex(io)
	}

	when 'User enters the commands', {
		bib.run()
	}

	then 'User is informed about the false input', {
		io.getPrints().shouldHave("Entry type not valid")
	}
}

scenario "User can save the reference after creation", {
	given 'User starts executing program', {
		io = new StubIO("n", "create", "article", "author", "Koira, Kille", "year", "1999", "title", "Haukku", "", "save", "")
		bib = new Bibtex(io)
	}

	when 'User enters the commands', {
		bib.run()
	}

	then 'User is informed about the false input', {
		io.getPrints().shouldHave("References added to the file")
	}
}
