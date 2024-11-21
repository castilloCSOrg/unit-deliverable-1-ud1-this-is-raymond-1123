/********************************************
*	AUTHORS:	Raymond Alcala
* COLLABORATORS: n/a
*	LAST MODIFIED:	10/17/24
********************************************/

/********************************************
*	Triangle Solver
*********************************************
*	PROGRAM DESCRIPTION:
*	Takes input from a user and uses it to parse information about a triangle
*********************************************
* WHY?
* I am a math tutor and enjoy doing math, I 
* wanted to see if I could make a program that
* could solve any general triangle. This could
* also be used by people I teach to check their
* answers.
*********************************************
*	STATIC METHODS:
* nonZero(double 1), nonZero(double 1, double 2, double 3), degreesToRadians(double 1), runProgram(), runProgramIndefinitely()
*********************************************/

public class Main 
{
  /***** CONSTANT SECTION *****/

  public static void main(String[] args)
  {
    //TriangleSolver triangle = new TriangleSolver();
    //triangle.setTriangle();
    //triangle.setSidesAndAngles();
    //triangle.solveTriangle();
    runProgramIndefinitely();
    
  }
  /***** STATIC METHODS *****/

  /**
   * Creates a TriangleSolver object and calls the methods in the right order
   */
  public static void runProgram()
  {
    TriangleSolver triangle = new TriangleSolver();
    triangle.setTriangle();
    triangle.setSidesAndAngles();
    triangle.solveTriangle();
  }

  /**
   * Runs runProgram until user answers with 'n' or 'N', will repetitively ask question until you answer with 'Y', 'y', 'N', or 'n'
   */
  public static void runProgramIndefinitely()
  {
    char test = 'y';
    while (test == 'y' || test == 'Y')
    {
      runProgram();
      test = UtilityBelt.readChar("Do you want to solve more triangles? (y/n) >","YyNn");
    }
  }
}
