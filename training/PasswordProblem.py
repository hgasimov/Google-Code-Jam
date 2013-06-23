'''
Created on Mar 14, 2013

@author: huseyngasimov
'''
working_dir = '/Users/huseyngasimov/Programs/eclipse/workspace/CodeJam/inputoutput_files/PasswordProblem/' 
input_filename = 'A-large-practice.in'
output_filename = 'A-large-practice.out'
DEBUG = False

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')

for i in range(1, int(f.readline())+1):
    line = f.readline().strip().split()    
    A, B = int(line[0]), int(line[1])
    
    prob = []
    probs = f.readline().strip().split()        
    for n in range(len(probs)): 
        prob.append(float(probs[n])) 
    
    # E_k = P_{Ak}*(B-A+1+2k) + (1-P_{Ak})*(2B-A+2+2k)
    # where P_{Ak} = p_1 * p_2 * ... * p_{A-k}
    # We'll start from the end (k=A) in the following loop 
    E = [B+2] # 'Enter' case
    p, k = 1, A    
    for j in range(A+1):
        expec = p*(B - A + 1 + 2*k) + (1-p)*(2*B - A + 2 + 2*k)  
        E.append(expec)  
        if DEBUG: print('k = ' + str(k) + ', expec = ' + str(expec))    
        k -= 1
        if (j < A): p *= prob[j] # just for the last iteration
    
    min_E = round(min(E), 7)
    print('Case #' + str(i) + ': ' + '%.6f'%min_E)
    fw.write('Case #' + str(i) + ': ' + '%.6f'%min_E + '\n')

fw.close()
f.close()
