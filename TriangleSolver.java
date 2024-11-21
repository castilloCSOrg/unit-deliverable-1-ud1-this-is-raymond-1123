import java.util.*;


public class TriangleSolver {

    private boolean isRight = false;
    private boolean triangleImpossible = false;
    private int numAngles = 0;
    private int numSides = 0;
    private double angleA;
    private double angleB;
    private double angleC;
    private double sideA;
    private double sideB;
    private double sideC;
    private final String initalTriangle = "         c\r\n" + //
                "         ‸ \r\n" + //
                "        / \\\r\n" + //
                "       /   \\\r\n" + //
                "    A /     \\ B\r\n" + //
                "     /       \\\r\n" + //
                "    /         \\\r\n" + //
                "  b ‾‾‾‾‾‾‾‾‾‾‾ a\r\n" + //
                "         C\n";

    /**
     * Reads input from user and initializes "numAngles" and "numSides" variables (error checks till correct bounds)
     */
    public void setTriangle()
    {
        while((numAngles + numSides) < 3)
        {
            numSides = UtilityBelt.readInt("How many sides do you know in your triangle? ", 1, 3);
            numAngles = UtilityBelt.readInt("How many angles do you know in your triangle? ", 0, 3);
            if((numAngles + numSides) < 3)
            {
                System.out.println("Not enough information available to parse rest of triangle, please find more information!");
            }
        }
    }

    /**
     * Reads input from user and initializes "sides" and "angles" variables (error checks till correct bounds and count)
     */
    public void setSidesAndAngles()
    {
        System.out.println("\n" + initalTriangle);
        System.out.println("Write down what corresponds to the triangle\n");
        System.out.println("Sides first please!");
        while(!(sidesCountCheck()))
        {
            sideA = UtilityBelt.readDouble("A: ", 0, 99);
            sideB = UtilityBelt.readDouble("B: ", 0 , 99);
            sideC = UtilityBelt.readDouble("C: ", 0 , 99);
            if (!sidesCountCheck())
            {
                System.out.println("That doesn't match what you told me earlier, try again liar!");
            }
        }
        System.out.println("Sides done, now angles (degrees) please!");
        while(!(anglesCountCheck()))
        {
            angleA = UtilityBelt.readDouble("a: ", 0, 180);
            angleB = UtilityBelt.readDouble("b: ", 0 , 180);
            angleC = UtilityBelt.readDouble("c: ", 0 , 180);
            if (!anglesCountCheck())
            {
                System.out.println("That doesn't match what you told me earlier, try again liar!");
            }
            if ((angleA + angleB + angleC) != 180 && nonZero(angleA, angleB, angleC))
            {
                System.out.println("That isn't possible for a triangle, invalid amount of degrees!");
                angleA = 0;
                angleB = 0;
                angleC = 0;
            }
        }
        twoAngleSolve();
        System.out.println("Perfect! Now your triangle is properly initialized.\n");
    }

    /**
     * Checks if a double given is greater than 0
     * @param num double of any value
     * 
     * @return returns true if num is greater than 0
     */
    public static boolean nonZero(double num)
    {
        if (num > 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Checks if 3 doubles given are greater than 0
     * @param num1 double of any value
     * @param num2 double of any value
     * @param num3 double of any value
     * 
     * @return returns true if num is greater than 0
     */
    public static boolean nonZero(double num1, double num2, double num3)
    {
        if (num1 > 0 && num1 > 0 && num3 > 0)
        {
            return true;
        }
        return false;
    }


    /**
     * Checks if the user input the right amount of sides
     * 
     * @return returns true if the amount of sides given matches what was told in intialization, returns false other wise
     */
    public boolean sidesCountCheck()
    {
        int countedSides = 0;
        if (sideA > 0)
        {
            countedSides++;
        }
        if (sideB > 0)
        {
            countedSides++;
        }
        if (sideC > 0)
        {
            countedSides++;
        }
        return countedSides == numSides;
    }

    /**
     * Checks how many sides are initialized in the terminal
     * 
     * @return returns the number of sides that have been initialized
     */
    public int sidesCheck()
    {
        int countedSides = 0;
        if (sideA > 0)
        {
            countedSides++;
        }
        if (sideB > 0)
        {
            countedSides++;
        }
        if (sideC > 0)
        {
            countedSides++;
        }
        return countedSides;
    }

    /**
     * Checks if the user input the right amount of angles
     * 
     * @return returns true if the amount of angles given matches what was told in intialization, returns false other wise
     */
    public boolean anglesCountCheck()
    {
        int countedAngles = 0;
        if (angleA > 0)
        {
            countedAngles++;
        }
        if (angleB > 0)
        {
            countedAngles++;
        }
        if (angleC > 0)
        {
            countedAngles++;
        }
        return countedAngles == numAngles;
    }

    /**
     * Checks how many angles are intialized in the terminal
     * 
     * @return returns the number of angles that are set
     */
    public int anglesCheck()
    {
        int countedAngles = 0;
        if (angleA > 0)
        {
            countedAngles++;
        }
        if (angleB > 0)
        {
            countedAngles++;
        }
        if (angleC > 0)
        {
            countedAngles++;
        }
        return countedAngles;
    }

    /**
     * Changes angles in degrees to radians for trigonometric functions
     * 
     * @param degrees a double that you want to change into radians for math operations, does not change instance variable value
     * 
     * @return returns radians for use in trigonometric functions
     */
    public static double degreesToRadians(double degrees)
    {
        return degrees * (Math.PI / 180);
    }

    /**
     * Finds the size of the last angle if two angles are already known and updates instance variables
     */
    public void twoAngleSolve()
    {
        if (anglesCheck() == 2)
        {
            if (angleA == 0)
            {
                angleA = (180.0 - angleB) - angleC;
            }
            else if (angleB == 0)
            {
                angleB = (180.0 - angleA) - angleC;
            }
            else if (angleC == 0)
            {
                angleC = (180.0 - angleA) - angleB;
            }
        }
    }

    /**
     * Checks for ambiguous SSA case and whether it can be solved or has no solution, sets triangleImpossible to true if 0 cases
     * 
     * @return returns true if triangle has two possible solutions, returns false if 0 or 1 solution
     */
    public boolean isAmbiguous()
    {
        if ((sidesCheck() == 2 && anglesCheck() == 1) && (nonZero(angleA, sideA, sideB) || nonZero(angleA, sideA, sideC) || nonZero(angleB, sideA, sideB) || nonZero(angleB, sideB, sideC) || nonZero(angleC, sideA, sideC) || nonZero(angleC, sideB, sideC)))
        {
            if (sideA == sideB || sideA == sideC || sideB == sideC )
            {
                isoscelesSolve();
                return false;
            }
            if (angleA > 90)
            {
                if (nonZero(sideA) && (sideA <= sideB || sideA <= sideC))
                {
                    triangleImpossible = true;
                }
                return !(sideA > sideB && sideA > sideC);
            }
            if (angleB > 90)
            {
                if (nonZero(sideB) && (sideB <= sideA || sideB <= sideC))
                {
                    triangleImpossible = true;
                }
                return !(sideB > sideA && sideB > sideC);
            }
            if (angleC > 90)
            {
                if (nonZero(sideC) && (sideC <= sideA || sideC <= sideB))
                {
                    triangleImpossible = true;
                }
                return !(sideC > sideA && sideC > sideB);
            }
            if (nonZero(angleA) && nonZero(sideB))
            {
                if(((double) Math.round(sideB * Math.sin(degreesToRadians(angleA)) / sideA * 100) / 100.0) > 1)
                {
                    triangleImpossible = true;
                }
                isRight = ((double) Math.round(sideB * Math.sin(degreesToRadians(angleA)) / sideA * 100) / 100.0) == 1;
                return !(((double) Math.round(sideB * Math.sin(degreesToRadians(angleA)) / sideA * 100) / 100.0) >= 1);
            }
            if (nonZero(angleA) && nonZero(sideC))
            {
                if(((double) Math.round(sideC * Math.sin(degreesToRadians(angleA)) / sideA * 100) / 100.0) > 1)
                {
                    triangleImpossible = true;
                }
                isRight = ((double) Math.round(sideC * Math.sin(degreesToRadians(angleA)) / sideA * 100) / 100.0) == 1;
                return !(((double) Math.round(sideC * Math.sin(degreesToRadians(angleA)) / sideA * 100) / 100.0) >= 1);
            }
            if (nonZero(angleB) && nonZero(sideA))
            {
                if(((double) Math.round(sideA * Math.sin(degreesToRadians(angleB)) / sideB * 100) / 100.0) > 1)
                {
                    triangleImpossible = true;
                }
                isRight = ((double) Math.round(sideA * Math.sin(degreesToRadians(angleB)) / sideB * 100) / 100.0) == 1;
                return !(((double) Math.round(sideA * Math.sin(degreesToRadians(angleB)) / sideB * 100) / 100.0) >= 1);
            }
            if (nonZero(angleB) && nonZero(sideC))
            {
                if(((double) Math.round(sideC * Math.sin(degreesToRadians(angleB)) / sideB * 100) / 100.0) > 1)
                {
                    triangleImpossible = true;
                }
                isRight = ((double) Math.round(sideC * Math.sin(degreesToRadians(angleB)) / sideB * 100) / 100.0) == 1;
                return !(((double) Math.round(sideC * Math.sin(degreesToRadians(angleB)) / sideB * 100) / 100.0) >= 1);
            }
            if (nonZero(angleC) && nonZero(sideA))
            {
                if(((double) Math.round(sideA * Math.sin(degreesToRadians(angleC)) / sideC * 100) / 100.0) > 1)
                {
                    triangleImpossible = true;
                }
                isRight = ((double) Math.round(sideA * Math.sin(degreesToRadians(angleC)) / sideC * 100) / 100.0) == 1;
                return !(((double) Math.round(sideA * Math.sin(degreesToRadians(angleC)) / sideC * 100) / 100.0) >= 1);
            }
            if (nonZero(angleC) && nonZero(sideB))
            {
                if(((double) Math.round(sideB * Math.sin(degreesToRadians(angleC)) / sideC * 100) / 100.0) > 1)
                {
                    triangleImpossible = true;
                }
                isRight = ((double) Math.round(sideB * Math.sin(degreesToRadians(angleC)) / sideC * 100) / 100.0) == 1;
                return !(((double) Math.round(sideB * Math.sin(degreesToRadians(angleC)) / sideC * 100) / 100.0) >= 1);
            }
            return true;
        }
        return false;
    }

    /**
     * Checks if triangle has 0, 1, or 2 solutions and prints out the solutions with the triangle shape, if impossible doesn't print anything other than impossible message
     */
    public void solveTriangle()
    {
        if (triangleImpossible)
        {
            System.out.println("This triangle is IMPOSSIBLE!");
            return;
        }
        switch(sidesCheck())
        {
            case 1:
                oneSideSolve();
                triangleShape();
                break;
            case 2:
                if (isAmbiguous())
                {
                    ambiguousSolve();
                    break;
                }
                else if (triangleImpossible)
                {
                    System.out.println("This triangle is IMPOSSIBLE!");
                    break;
                }
                else if (anglesCheck() == 1)
                {
                    sasSolve();
                    oneAngleSolve();
                    triangleShape();
                    break;
                }
                else
                {
                    oneSideSolve();
                    triangleShape();
                    break;
                }
            case 3:
                sssSolve();
                triangleShape();
                break;


        }
    }

    /**
     * Solves for the other angles and sides if two congruent sides and one angles are known.
     */
    public void isoscelesSolve()
    {
        if(nonZero(angleA, sideA, sideB))
                {
                    angleB = Math.toDegrees(Math.asin((sideB / sideA) * Math.sin(degreesToRadians(angleA))));
                    twoAngleSolve();
                    solveTriangle();
                   
                }
                else if (nonZero(angleA, sideA, sideC))
                {
                    angleC = Math.toDegrees(Math.asin((sideC / sideA) * Math.sin(degreesToRadians(angleA))));
                    twoAngleSolve();
                    solveTriangle();
                   
                }
                else if (nonZero(angleB, sideA, sideB))
                {
                    angleA = Math.toDegrees(Math.asin((sideA / sideB) * Math.sin(degreesToRadians(angleB))));
                    twoAngleSolve();
                    solveTriangle();
                   
                }
                else if (nonZero(angleB, sideB, sideC))
                {
                    angleC = Math.toDegrees(Math.asin((sideC / sideB) * Math.sin(degreesToRadians(angleB))));
                    twoAngleSolve();
                    solveTriangle();
                    
                }
                else if (nonZero(angleC, sideA, sideC))
                {
                    angleA = Math.toDegrees(Math.asin((sideA / sideC) * Math.sin(degreesToRadians(angleC))));
                    twoAngleSolve();
                    solveTriangle();
                    
                }
                else if (nonZero(angleC, sideB, sideC))
                {
                    angleB = Math.toDegrees(Math.asin((sideB / sideC) * Math.sin(degreesToRadians(angleC))));
                    twoAngleSolve();
                    solveTriangle();
                    
                }

    }

    /**
     * Solves for other two sides if at least two angles and one side are known
     */
    public void oneSideSolve()
    {
        if (nonZero(sideA) && nonZero(angleA))
        {
            sideB = Math.sin(degreesToRadians(angleB)) * sideA / Math.sin(degreesToRadians(angleA));
            sideC = Math.sin(degreesToRadians(angleC)) * sideA / Math.sin(degreesToRadians(angleA));
        }
        else if (nonZero(sideB) && nonZero(angleB))
        {
            sideA = Math.sin(degreesToRadians(angleA)) * sideB / Math.sin(degreesToRadians(angleB));
            sideC = Math.sin(degreesToRadians(angleC)) * sideB / Math.sin(degreesToRadians(angleB));
        }
        else if (nonZero(sideC) && nonZero(angleC))
        {
            sideA = Math.sin(degreesToRadians(angleA)) * sideC / Math.sin(degreesToRadians(angleC));
            sideB = Math.sin(degreesToRadians(angleB)) * sideC / Math.sin(degreesToRadians(angleC));
        }
    }

    /**
     * Solves for other two angles if at least two sides are known and only one angle
     */
    public void oneAngleSolve()
    {
        if (nonZero(angleA))
        {
            angleB = Math.toDegrees(Math.asin(sideB * Math.sin(degreesToRadians(angleA)) / sideA));
            angleC = Math.toDegrees(Math.asin(sideC * Math.sin(degreesToRadians(angleA)) / sideA));
        }
        else if (nonZero(angleB))
        {
            angleA = Math.toDegrees(Math.asin(sideA * Math.sin(degreesToRadians(angleB)) / sideB));
            angleC = Math.toDegrees(Math.asin(sideC * Math.sin(degreesToRadians(angleB)) / sideB));
        }
        else if (nonZero(angleC))
        {
            angleA = Math.toDegrees(Math.asin(sideA * Math.sin(degreesToRadians(angleC)) / sideC));
            angleB = Math.toDegrees(Math.asin(sideB * Math.sin(degreesToRadians(angleC)) / sideC));
        }
    }

    /**
     * Solves for all angles if all sides are known
     */
    public void sssSolve()
    {
        angleA = Math.toDegrees(Math.acos((sideA * sideA - sideB * sideB - sideC * sideC)/ (-2.0 * sideB * sideC)));
        angleB = Math.toDegrees(Math.acos((sideB * sideB - sideA * sideA - sideC * sideC)/ (-2.0 * sideA * sideC)));
        twoAngleSolve();
    }

    /**
     * Solves all SAS triangles
     */
    public void sasSolve()
    {
        if (!isAmbiguous())
        {
            if (nonZero(angleA))
            {
                sideA = Math.sqrt(sideB * sideB + sideC * sideC - 2 * sideB * sideC * Math.cos(degreesToRadians(angleA)));
            }
            else if (nonZero(angleB))
            {
                sideB = Math.sqrt(sideA * sideA + sideC * sideC - 2 * sideA * sideC * Math.cos(degreesToRadians(angleB)));
            }
            else if (nonZero(angleC))
            {
                sideC = Math.sqrt(sideB * sideB + sideA * sideA - 2 * sideB * sideA * Math.cos(degreesToRadians(angleC)));
            }
        }

    }

    /**
     * If triangle is ambiguous (shown through isAmbiguous method) solves and prints one triangle and finds the other possiblity
     */
    public void ambiguousSolve()
    {
        if (isAmbiguous())
        {
            if (!triangleImpossible)
            {
                if(nonZero(angleA, sideA, sideB))
                {
                    angleB = Math.toDegrees(Math.asin((sideB / sideA) * Math.sin(degreesToRadians(angleA))));
                    twoAngleSolve();
                    solveTriangle();
                    if (Math.round(angleA) != 90 && Math.round(angleB) != 90 && Math.round(angleC) != 90)
                    {
                        System.out.println("This triangle is ambiguous, so another triangle exists:\n");
                        angleB = 180 - angleB;
                        angleC = 0;
                        sideC = 0;
                        twoAngleSolve();
                        solveTriangle();
                    }
                }
                else if (nonZero(angleA, sideA, sideC))
                {
                    angleC = Math.toDegrees(Math.asin((sideC / sideA) * Math.sin(degreesToRadians(angleA))));
                    twoAngleSolve();
                    solveTriangle();
                    if (Math.round(angleA) != 90 && Math.round(angleB) != 90 && Math.round(angleC) != 90)
                    {
                        System.out.println("This triangle is ambiguous, so another triangle exists:\n");
                        angleC = 180 - angleC;
                        angleB = 0;
                        sideB = 0;
                        twoAngleSolve();
                        solveTriangle();
                    }
                }
                else if (nonZero(angleB, sideA, sideB))
                {
                    angleA = Math.toDegrees(Math.asin((sideA / sideB) * Math.sin(degreesToRadians(angleB))));
                    twoAngleSolve();
                    solveTriangle();
                    if (Math.round(angleA) != 90 && Math.round(angleB) != 90 && Math.round(angleC) != 90)
                    {
                        System.out.println("This triangle is ambiguous, so another triangle exists:\n");
                        angleA = 180 - angleA;
                        angleC = 0;
                        sideC = 0;
                        twoAngleSolve();
                        solveTriangle();
                    }
                }
                else if (nonZero(angleB, sideB, sideC))
                {
                    angleC = Math.toDegrees(Math.asin((sideC / sideB) * Math.sin(degreesToRadians(angleB))));
                    twoAngleSolve();
                    solveTriangle();
                    if (Math.round(angleA) != 90 && Math.round(angleB) != 90 && Math.round(angleC) != 90)
                    {
                        System.out.println("This triangle is ambiguous, so another triangle exists:\n");
                        angleC = 180 - angleA;
                        angleA = 0;
                        sideA = 0;
                        twoAngleSolve();
                        solveTriangle();
                    }
                }
                else if (nonZero(angleC, sideA, sideC))
                {
                    angleA = Math.toDegrees(Math.asin((sideA / sideC) * Math.sin(degreesToRadians(angleC))));
                    twoAngleSolve();
                    solveTriangle();
                    if (Math.round(angleA) != 90 && Math.round(angleB) != 90 && Math.round(angleC) != 90)
                    {
                        System.out.println("This triangle is ambiguous, so another triangle exists:\n");
                        angleA = 180 - angleA;
                        angleB = 0;
                        sideB = 0;
                        twoAngleSolve();
                        solveTriangle();
                    }
                }
                else if (nonZero(angleC, sideB, sideC))
                {
                    angleB = Math.toDegrees(Math.asin((sideB / sideC) * Math.sin(degreesToRadians(angleC))));
                    twoAngleSolve();
                    solveTriangle();
                    if (Math.round(angleA) != 90 && Math.round(angleB) != 90 && Math.round(angleC) != 90)
                    {
                        System.out.println("This triangle is ambiguous, so another triangle exists:\n");
                        angleB = 180 - angleB;
                        angleA = 0;
                        sideA = 0;
                        twoAngleSolve();
                        solveTriangle();
                    }
                }
            }
        }
    }

    /**
     * Prints formatted triangle with inputted angles and sides
     */
    public void triangleShape()
    {
        String triangle = String.format("%14.2f\r\n" + 
                "           ‸ \r\n" + 
                "          / \\\r\n" + 
                "         /   \\\r\n" + 
                "%7.2f /     \\ %.2f\r\n" + 
                "       /       \\\r\n" + 
                "      /         \\\r\n" + 
                "%6.2f‾‾‾‾‾‾‾‾‾‾‾%.2f\r\n" + 
                "%14.2f\n", angleC, sideA, sideB, angleB, angleA, sideC);
        System.out.println(triangle);
        infoDump();
    }

    /**
     * Prints angles and sides along with their values
     */
    public void infoDump()
    {
        System.out.printf("A:%f\nB:%f\nC:%s\na:%f\n" + //
                        "b:%f\n" + //
                        "c:%f\n" + //
                        "",sideA,sideB,sideC,angleA,angleB,angleC);
    }

    
}