__author__ = 'shleger'
print("www")

import time
import os
import sys
import re
import subprocess




#os.system('free -m ')

output=subprocess.check_output(["echo", "Hello World!"], shell=True)
#output = subprocess.check_output(['ls', '-1'])
#print 'Have %d bytes in output' % len(output)
print(output)

filename = "write.log"
file = open(filename, "w",encoding='utf-8')
file.write("out")
file.close()

str= "str fff \n qqqq\n"
print(str)

str=re.sub('\n','',str)

print(str)




"""

sys.exit(0)

timeout = 10
first_time = time.time()
last_time = first_time
while(True):
    pass #do something here
    new_time = time.time()
    if  new_time - last_time > timeout:
        last_time = new_time
        print "Its been %f seconds" % (new_time - first_time)
"""

