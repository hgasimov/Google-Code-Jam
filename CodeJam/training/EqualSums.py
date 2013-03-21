'''
Created on Mar 21, 2013

@author: huseyngasimov
'''

def calc(s1, s2):
    if DEBUG: 
        print('s1 is: ')
        print(s1)
        print('s2 is: ')
        print(s2)        
    
    sum1, sum2 = sum(s1), sum(s2)
    if sum1 == sum2:
        return s1, s2
    elif len(s1) > 1 and sum1 > sum2:
        for j in range(len(s1)):
            s1j = s1[j]
            if sum1 - s1j >= sum2:                
                s1.pop(j)
                rs11, rs12 = calc(s1, s2)
                if rs11 == []:
                    if sum1 - s1j >= sum2 + s1j:
                        s2.append(s1j)
                        rs21, rs22 = calc(s1, s2)
                        if rs21 == []:
                            s2.pop()
                        else:
                            return rs21, rs22
                    
                    s1.insert(j, s1j)
                else:
                    return rs11, rs12
            else:
                return [], []
        
        return [], []  # after no result from 'for' loop          
    else:
        return [], []

working_dir = '/Users/huseyngasimov/git/CodeJam/CodeJam/inputoutput_files/EqualSums/' 
input_filename = 'example_in.txt'
output_filename = 'example_out.txt'
DEBUG = False

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')

for i in range(1, int(f.readline())+1):    
    line = f.readline().strip().split()
    #print(line)    
    N = int(line[0])
    s1 = []
    s2 = []    
    for j in range(1, N+1):
        s1.append(int(line[j]))
         
    #s1 = [1, 2, 3]
    s1.sort()
    rs1, rs2 = calc(s1, s2)
    print('Result: ')
    print(rs1)
    print(rs2)




fw.close()
f.close()
