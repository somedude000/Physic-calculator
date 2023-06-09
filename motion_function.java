public class motion_function
{
    private double initVelocity;
    private double finalVelocity;
    private double initPos;
    private double finalPos;
    private double acceleration;
    private double time;

    public motion_function(){
        initVelocity = 0;
        finalVelocity = 0;
        finalPos = 0;
        initPos = 0;
        acceleration = 0;
        time = 0;
    }

    //method to calculate time with velocity and displacement 
    public double calcTime_VelDis(double iniVel, double finalVel, double dis) {
        double result = 2*dis/(iniVel+finalVel);
        return result;
    }
    
    //method to calculate distance with time, initial velocity and acceleration 
    public double calcDis_TimeAccInit(double iniVel, double tim, double acc){
        double result = iniVel*tim + .5*acc*tim*tim;
        return result;
    }

    //method to calculate accleration with distance, velocity
    public double calcAcc_DisVel(double iniVel, double finalVel, double dis){
        double result = (finalVel*finalVel - iniVel*iniVel)/(2*dis);
        return result;
    }

    //method to calculate mass with force and acceleration
    public double calcMass_ForcAcc(double forc, double acc){
        double result = forc/acc;
        return result;
    }

    //method to calculate distance with time, velocity
    public double calcDis_TimeVel(double tim, double initVel, double finalVel){
        double result = .5*(initVel+finalVel)*tim;
        return result;
    }

    //method to calculate distance with accleration, velocity
    public double calcDis_AccVel(double acc, double initVel, double finalVel){
        double result = Math.sqrt((finalVel*finalVel - initVel*initVel)/(2*acc));
        return result;
    }

    //method to calculate final position with self-selected method
    public double calcFinalPos_Main(){
        if(time == 0){  //time is not known
            if (acceleration != 0){
                return (initPos + calcDis_AccVel(acceleration, initVelocity, finalVelocity));
            }
            return 0;
        }
        else{
            if(finalVelocity == 0){ //final velocity is not known
                return (initPos + calcDis_TimeAccInit(initVelocity, time, acceleration));
            }
            if(acceleration == 0){  // acceleration is not known
                return (initPos + calcDis_TimeVel(time, initVelocity, finalVelocity));
            }
            return 0;
        }
    }

    //method to calculate final velocity with self-selected method
    public double calcFinalVel_Main(){
        if (acceleration == 0){
            return (2*(finalPos-initPos)/time)-initVelocity;
        }
        else{
            if(time == 0){
                return Math.sqrt(initVelocity*initVelocity+(2*acceleration*(finalPos-initPos)));
            }
            if(initVelocity == 0){
                return ((.5*acceleration*time*time)-initPos+finalPos)/time;
            }
            return 0; 
        }
    }

    public double calcInitVel_Main(){
        if (acceleration == 0){
            return (2*(finalPos-initPos)/time)-finalVelocity;
        }
        else{
            if(time == 0){
                return Math.sqrt(finalVelocity*finalVelocity-(2*acceleration*(finalPos-initPos)));
            }
            if(finalVelocity == 0){
                return (finalPos-(.5*acceleration*time*time)-initPos)/time;
            }
            return 0; 
        }
    }

    public double calcAcc_Main(){
        if (time == 0){
            return (finalVelocity*finalVelocity-initVelocity*initVelocity)/(2*(finalPos-initPos));
        }
        else{
            if (initVelocity == 0){
                return 2*(finalVelocity*time-(finalPos-initPos))/(time*time);
            }
            if(finalVelocity == 0){
                return -2*(finalVelocity*time-(finalPos-initPos))/(time*time);
            }
            return 0;
        }
    }

    //method to renew the variable with input
    public void renewVar(double initVel, double finalVel, double tim, double acc, double initP, double finalP){
        initVelocity = initVel;
        finalVelocity = finalVel;
        time = tim;
        acceleration = acc;
        initPos = initP;
        finalPos = finalP;
    }
}