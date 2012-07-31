all:
	latex doc.tex
#	bibtex doc
	latex doc.tex
	latex doc.tex
#	dvips -u /dev/null -t letter -o gsn.ps gsn
	dvips -t letter -f -Pcmz doc.dvi -o doc.ps
	ps2pdf -dPDFSETTINGS=/prepress doc.ps &
        
view:
	ggv doc.ps&

viewgv:
	evince doc.ps&

clean:
	rm -rf *.aux *.dvi *.log
