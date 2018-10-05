package game;


//orginal celle?
//verdi 

public class Cell {
int input;
boolean isFree;
int value;
	
public Cell(int input, boolean isFree) { //brukes for å lage selve brettet
	if (isFree) {
		this.value = 0;
	}
	else {
		this.value = input;
	}
}
public Cell(int input) { //burkes når spilleren skal sette input
	this.isFree = true;
	this.value = input;
}

public int getCell() { 
	return value;
}



}

