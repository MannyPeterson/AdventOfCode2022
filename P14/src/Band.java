import processing.core.PApplet;

public class Band extends PApplet {
	
	public static final int AIR = 0;
	public static final int ROCK = 1;
	public static final int SAND = 2;
	
	private final int ROWS = 800;
	private final int COLS = 1000;
	
	private AoCCoordinate particle;

	private int[][] cave;
/*	
    public static void main(String[] args) {
        PApplet.main("Sand");
        
        Sand sand = new Sand();
        
        sand.draw();
    }
*/	
	public Band() {

		
		this.cave = new int[ROWS][COLS];
		
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				this.cave[row][col] = AIR;
			}
		}
		
		particle = new AoCCoordinate(ROWS, COLS, 0, 500);
		
		
		
		
	}
	
	
	
    public void settings(){
        size(1000, 800);
    }
	public void draw() {
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				if(this.cave[row][col] == AIR) {
					stroke(0x3486eb);
				} else if(this.cave[row][col] == ROCK) {
					stroke(0x171310);
				} else if (this.cave[row][col] == SAND) {
					stroke(0xe3a509);
				}
				
				point(col, row);
			}
		}
	}
	
	
	
}
