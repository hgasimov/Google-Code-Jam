'''
Created on Apr 25, 2013

@author: huseyngasimov
'''

def reveal(group, found, penalty, pattern, letter):
    #print('letter = ' + letter)
    for i in range(len(group)):
        index = group[i] # dictionary position
        if found[index]: continue # if already found        
        w = list(D[index])
        penal = 1
        for j in range(len(w)):            
            if w[j] == letter:
                pattern[index][j] = letter
                penal = 0 # found => no penalty
                        
        penalty[index] += penal                
    '''    
    for i in range(len(group)):
        index = group[i] 
        print([D[index]] + [str(found[index])] + pattern[index] + [penalty[index]])    
    '''

# are the lists equal?
def equal(l1, l2):
    if not len(l1) == len(l2): return False
    for i in range(len(l1)): 
        if not l1[i] == l2[i]: return False
    return True
    
def relevant(group, found, letter):
    for i in range(len(group)):
        index = group[i] # dictionary position
        if (not found[index]) and (letter in D[index]):
            return True
    return False



def calcGroup(group, found, penalty, pattern, strategy, start_index):
    if (len(group) == 0): return
    if (len(group) == 1):
        found[group[0]] = True
        return
    
    for y in range(start_index, len(strategy)): # for each letter of strategy     
        if relevant(group, found, strategy[y]): break    
    if (y == len(strategy)): return
    
    reveal(group, found, penalty, pattern, strategy[y])                
    
    # if some pattern is different from the rest, then mark it as found
    ingroup = [False for k in range(len(group))]
    for i in range(len(group)-1):
        index1 = group[i]        
        if found[index1] or ingroup[i]: continue
        newGroup = [index1]
        ingroup[i] = True
        for j in range(i+1, len(group)):
            index2 = group[j]
            if found[index2]  or ingroup[j]: continue
            if equal(pattern[index1], pattern[index2]):
                newGroup.append(index2)
                ingroup[j] = True
        
        #print(newGroup)
        calcGroup(newGroup, found, penalty, pattern, strategy, y+1)
                
    
        
        
    
working_dir = '/Users/huseyngasimov/git/CodeJam/CodeJam/inputoutput_files/TheKillerWord/' 
input_filename = 'B-large-practice.in'
output_filename = 'B-large-practice.out'
maxLength = 10

DEBUG = True
testCase = 3

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')

for j in range(1, int(f.readline())+1):
    line = f.readline().strip().split()
    N, M = int(line[0]), int(line[1])    
    
    D = [] # dictionary
    l = [[] for i in range(maxLength+1)] # group words by their lengths
    for i in range(N):
        word = f.readline().strip() 
        D.append(word)
        l[len(word)].append(i) # append([index, word, found, penalty])
    
    L = [] # strategy list
    for i in range(M):
        L.append(f.readline().strip())
    
    result = ''
    for x in range(M):   
        strategy = list(L[x])
        max_penalty, max_index = -1, -1
        found = [False for k in range(N)]
        pattern = [['_' for k in range(len(D[y]))] for y in range(N)]
        penalty = [0 for k in range(N)]
                                    
        for i in range(maxLength, 0, -1): # for the each word length group
            if len(l[i]) == 0: continue # if there's no word of this length            
            group = l[i]                                    
            calcGroup(group, found, penalty, pattern, strategy, 0)
            
        for k in range(N):
            if penalty[k] > max_penalty or (penalty[k] == max_penalty and k < max_index):
                max_penalty = penalty[k]
                max_index = k
            
        
        result += ' ' + D[max_index]
                     
    print('Case #' + str(j) + ':' + result)
    fw.write('Case #' + str(j) + ':' + result + '\n')    


fw.close()
f.close()
