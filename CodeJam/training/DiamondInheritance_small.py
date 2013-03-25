'''
Created on Mar 24, 2013

@author: huseyngasimov
'''
def checkCycles(n):    
    nodes = graph[n]
    checked[n] = True
    for i in nodes:
        rcnodes = checkCycles(i) 
        if rcnodes is None: # if there's a cycle           
            return None
        elif len(rcnodes & nodes) > 0:
            return None
        else:
            nodes = nodes | rcnodes            
            
    return nodes

working_dir = '/Users/huseyngasimov/git/CodeJam/CodeJam/inputoutput_files/DiamondInheritance/' 
input_filename = 'A-small-practice.in'
output_filename = 'A-small-practice.out'
DEBUG = True
testcase =  30

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')

for i in range(1, int(f.readline())+1):            
    N = int(f.readline().strip())
    graph = [set([])]*N
    checked = [False]*N # whether cycle check has been done at this node
    found = False

    for j in range(N):
        line = f.readline().strip().split()
        if len(line) > 1:            
            for k in range(1, len(line)):
                c = int(line[k]) - 1 # because numbering starts from 0 here
                graph[j] = graph[j] | set([c])                            
    
    
    for k in range(N-1, -1, -1):
        if checked[k]: continue
        if checkCycles(k) is None:            
            print('Case #' + str(i) + ': Yes')
            fw.write('Case #' + str(i) + ': Yes\n')
            found = True # cycle found
            break
     
    if not found: 
        print('Case #' + str(i) + ': No')
        fw.write('Case #' + str(i) + ': No\n')
        


                        
fw.close()
f.close()
