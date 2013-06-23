'''
Created on Mar 24, 2013

@author: huseyngasimov
'''

working_dir = '/Users/huseyngasimov/git/CodeJam/CodeJam/inputoutput_files/DiamondInheritance/' 
input_filename = 'A-large-practice.in'
output_filename = 'A-large-practice.out'
DEBUG = False

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')

for i in range(1, int(f.readline())+1):            
    N = int(f.readline().strip())
    graph = [set([])]*N
    reach_list = [set([])]*N
    unchecked = set([]) # whether cycle check has been done at this node
    found = False

    for j in range(N):
        line = f.readline().strip().split()
        if len(line) > 1:            
            unchecked.add(j)
            for k in range(1, len(line)):                
                c = int(line[k]) - 1 # because numbering starts from 0 here
                graph[j] = graph[j] | set([c])                                                            
    
    
    while len(unchecked) > 0 and not found:
        for j in range(N):
            if j in unchecked:   
                allChecked = True   
                for k in graph[j]:
                    if k in unchecked: 
                        allChecked = False
                        break
                    
                if allChecked:
                    unchecked.remove(j)
                    nodes = graph[j]
                    for k in graph[j]:
                        newreach = reach_list[k] 
                        if len(nodes & newreach) > 0: # if cycle is detected
                            found = True
                            break
                        else:
                            nodes = nodes | newreach
                    
                    if found: break
                    else: reach_list[j] = reach_list[j] | nodes
                    
    
    if found:            
        print('Case #' + str(i) + ': Yes')
        fw.write('Case #' + str(i) + ': Yes\n')
    else: 
        print('Case #' + str(i) + ': No')
        fw.write('Case #' + str(i) + ': No\n')
        
            
fw.close()
f.close()