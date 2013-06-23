'''
Created on Mar 21, 2013

@author: huseyngasimov
'''

working_dir = '/Users/huseyngasimov/git/CodeJam/CodeJam/inputoutput_files/SafetyinNumbers/' 
input_filename = 'A-large-practice.in'
output_filename = 'A-large-practice.out'
DEBUG = False

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')

for i in range(1, int(f.readline())+1):
    line = f.readline().strip().split()  
    s = []
    
    N = int(line[0])
    summ = 0
    for j in range(1, N+1):                
        sj = int(line[j])
        s.append([sj, False])
        summ += sj        
    
    if N == 1:
        print('Case #' + str(i) + ': 0')
        fw.write('Case #' + str(i) + ': 0\n')
        continue
    
    res = []
    removed = True
    sum_dash, N_dash = summ, N
    while removed:
        removed = False        
        k = (sum_dash + summ) / N_dash
        for j in range(N):
            if not s[j][1]: # if it's not been calculated yet
                min_sj = round(100 * (k - s[j][0]) / summ, 6)
                if min_sj < 0: 
                    res.append([j, 0])
                    s[j][1] = True # indicates that this index has already been calculated
                    removed = True # removed from the list to be calculated
                    sum_dash -= s[j][0]
                    N_dash -= 1

    
    k = (sum_dash + summ) / N_dash
    for j in range(N):
        if not s[j][1]: # if it's not been calculated yet
            min_sj = round(100 * (k - s[j][0]) / summ, 6)
            res.append([j, min_sj])            
    
    
    res.sort(key= lambda a: a[0], reverse=False)
    minPercent = ''
    for j in range(N):
        minPercent += ' ' + str(res[j][1])
              
    print('Case #' + str(i) + ':' + minPercent)
    fw.write('Case #' + str(i) + ':' + minPercent + '\n')


fw.close()
f.close()

