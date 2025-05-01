import sys
import subprocess

if  len(sys.argv) !=5:
    print("Usage: python3 run.py <int/float> <add/subtract/multiply/div> <operand1> <operand2>")
    sys.exit(1)

data_type = sys.argv[1]
operation=sys.argv[2]
operand1=sys.argv[3]
operand2=sys.argv[4]

try:
    result=subprocess.run(['ant','remove','jar'],capture_output=1,text=1,check=1)
except subprocess.CalledProcessError as e :
    print(f"Error occured in compilation: {e.stderr}")
    sys.exit(1)

command = ['java','-cp','dist/aarithmetic.jar','arbitraryarithmetic.MyInfArith',data_type,operation,operand1,operand2]
try :
    result=subprocess.run(command,capture_output=1,text=1,check=1)
    print(result.stdout)
except subprocess.CalledProcessError as e:
    print(f"Error occured in making jar file:{e.stderr}")
    sys.exit(1)
