/*------------------------------------------------------------------*/
/* Allen Myers                                                      */
/* Lab 4                                                            */
/* Figure the perimeter and area of a polygon                       */
/* surrounded by a circle                                           */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define FILE_IN "lab4.dat"
//#define FILE_IN "lab4sample.dat"
#define FILE_OUT "lab4.out"

int main(void)
{
	double radius,nSides,perimeter,area;			//variables
	FILE * input_file;					//pointer variable for the i/o files
	FILE * answer_file;
	input_file= fopen(FILE_IN,"r");

	if(input_file==NULL)					//is the input file good?
	{
		printf("Error on opening the input file \n");
		exit(EXIT_FAILURE);
	}

	answer_file=fopen(FILE_OUT,"w");
	if (answer_file==NULL)					//is the output file good?
	{
		printf("Error on opening the output file \n");
		exit(EXIT_FAILURE);
	}
		fprintf(answer_file, "\nAllen Myers.  Lab 4.\n\n");
                fprintf(answer_file, "            Number      Perimeter      Area Of  \n");
                fprintf(answer_file, " Radius    Of Sides    Of Polygon      Polygon  \n");
                fprintf(answer_file, "--------   --------   ------------   ----------- \n");

	while(fscanf(input_file,"%lf%lf",&radius,&nSides)==2)
	{

		perimeter=(2*nSides*radius*(sin(M_PI/nSides)));
		area=((0.5)*nSides*(pow(radius,2)*sin((2*M_PI)/nSides)));
 
								//print the information to the output file.
		fprintf(answer_file, "  %5.2f      %5.2f      %8.4f       %9.4f\n\n"
			,radius, nSides, perimeter,area);
	}
								//close the input and output files
	fclose(input_file);
	fclose(answer_file);

	return EXIT_SUCCESS;					//file ran successfully
}
/*------------------------------------------------------------------*/
