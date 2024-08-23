//Allen Myers. Lab5.
//compute.c file
//Function to compute the area and 
//circumference of a circle
#include "lab5.h"

void compute(double radius, double *area, double *cir)
{
 *area=M_PI*pow(radius,2);
 *cir=2*M_PI*radius;
 //return &area,&cir;

}


