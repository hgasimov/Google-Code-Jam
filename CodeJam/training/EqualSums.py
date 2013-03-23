'''
Created on Mar 21, 2013

@author: huseyngasimov
'''
def setToLine(s):
    line = ''
    for i in s: line += str(i) + ' '        
    return line
    
    
working_dir = '/Users/huseyngasimov/git/CodeJam/CodeJam/inputoutput_files/EqualSums/' 
input_filename = 'C-small-practice.in'
output_filename = 'C-small-practice.out'
DEBUG = False

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')

for i in range(1, int(f.readline())+1):    
    line = f.readline().strip().split()
    #print(line)    
    N = int(line[0])
    S = []
    for j in range(1, N+1):
        S.append(int(line[j]))
         
    k = sum(S) # upper bound for the sum
    x = [[]] + [None]*k # x[i] = list_i --means--> sum(list_i)=i
    calc = [] # list if calculated numbers
    found = False
    for s in S:
        calc.append(s)
        for base_sum in range(sum(calc), -1, -1):
            if x[base_sum] is not None:
                if x[base_sum + s] is None:
                    x[base_sum + s] = x[base_sum] + [s]
                else:
                    S1 = set(x[base_sum] + [s])
                    S2 = set(x[base_sum + s])
                    intersect = S1 & S2
                    S1 -= intersect
                    S2 -= intersect
                    print('Case #' + str(i) + ':\n' + setToLine(S1))
                    fw.write('Case #' + str(i) + ':\n' + setToLine(S1) + '\n')
                    print(setToLine(S2))
                    fw.write(setToLine(S2) + '\n')
                    
                    found = True
                    break
        
        if found: break

    if not found: 
        print('Case #' + str(i) + ':Impossible')
        fw.write('Case #' + str(i) + ':Impossible\n')
                        
fw.close()
f.close()
