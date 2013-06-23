'''
Created on Apr 11, 2013

@author: huseyngasimov
'''
working_dir = '/Users/huseyngasimov/git/CodeJam/CodeJam/inputoutput_files/GoroSort/' 
input_filename = 'D-small-practice.in'
output_filename = 'D-small-practice.out'
DEBUG = True

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')


for j in range(1, int(f.readline())+1):
    N = int(f.readline().strip())
    arr = [0] * N
    line = f.readline().strip().split()
    for i in range(N): 
        arr[i] = int(line[i])
    
    flips = 0 # number of flips
    for i in range(N-1):
        if not arr[i] == i+1:
            for k in range(i, N):
                if arr[k] == i+1:
                    arr[k] = arr[i]
                    arr[i] = i+1
                    flips += 1
    
                
    if DEBUG: print(line)
    print('Case #' + str(j) + ': ' + str(flips*2))
    fw.write('Case #' + str(j) + ': '  + str(flips*2) + '\n')    


fw.close()
f.close()
                    
