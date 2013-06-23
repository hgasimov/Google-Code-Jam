'''
Created on Mar 14, 2013

@author: huseyngasimov
'''
working_dir = '/Users/huseyngasimov/Programs/eclipse/workspace/CodeJam/inputoutput_files/DancingWiththeGooglers/' 
input_filename = 'B-large-practice.in'
output_filename = 'B-large-practice.out'
DEBUG = False

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')

for i in range(1, int(f.readline())+1):
    nums = f.readline().strip().split()
    #print(' '.join(nums))
    N, S, p = int(nums[0]), int(nums[1]), int(nums[2])
    
    if (p == 0):
        print('Case #' + str(i) + ': ' + str(N) + '  ||  ' + ' '.join(nums))
        fw.write('Case #' + str(i) + ': ' + str(N) + '\n')    
        continue
    
    count = 0
    used_s = 0
    for j in range(3, N+3):
        score = int(nums[j])
        if (score == 0): continue # it can be counted only when p=0 but this case 
                                  # is filtered out beforehand        
        if (3*p - 2 <= score):
            if DEBUG: print(str(score) + ' added')
            count += 1
        elif ((score >= 3*p - 4) and (used_s < S)):
            used_s += 1
            count += 1
            if DEBUG: print(str(score) + ' added')
            
    print('Case #' + str(i) + ': ' + str(count) + '  ||  ' + ' '.join(nums))
    fw.write('Case #' + str(i) + ': ' + str(count) + '\n')
    


fw.close()
f.close()