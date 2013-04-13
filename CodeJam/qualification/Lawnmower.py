'''
Created on Apr 12, 2013

@author: huseyngasimov
'''
import copy

def cutRow(row, lev):
    if row > N-1 or row < 0: return 0
    n = 0
    for i in range(M):
        if board[row][i] > lev: return 0
        
    for i in range(M):
        if board[row][i] == lev and (not done[row][i]): 
            n += 1
            done[row][i] = True    
    return n

def cutColumn(col, lev):
    if col > M-1 or col < 0: return 0
    n = 0
    for i in range(N):
        if board[i][col] > lev: return 0
    
    for i in range(N):
        if board[i][col] == lev and (not done[i][col]): 
            n += 1
            done[i][col] = True    
    return n

def createFalseMat():
    row = []
    for i in range(M): row.append(False)
    res = []
    for i in range(N): res.append(copy.copy(row))
    return res

def initBoard():
    row = []
    for i in range(M): row.append(0)
    res = []
    for i in range(N): res.append(copy.copy(row))
    return res


working_dir = '/Users/huseyngasimov/git/CodeJam/CodeJam/inputoutput_files/Lawnmower/' 
input_filename = 'B-large.in'
output_filename = 'B-large.out'
DEBUG = False
testCase = 3

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')

max_aij = 100

for j in range(1, int(f.readline())+1):
    line = f.readline().strip().split()
    N, M = int(line[0]), int(line[1])    
    
    avail = [0] * (max_aij + 1) # availability matrix
    done = createFalseMat() # cut successfully    
    board = initBoard()
    
    #print(board)
    for i in range(N):
        line = f.readline().strip().split() 
        #print(line)       
        for x in range(M): 
            board[i][x] = int(line[x])
            avail[board[i][x]] += 1
    
    possible = True    
    for level in range(max_aij, 0, -1):
        if avail[level] > 0:
            if DEBUG: print(avail[level])
            n = 0
            for x in range(N):
                if n < avail[level]: 
                    n += cutRow(x, level)
                else: break
                        
            for x in range(M):
                if n < avail[level]: 
                    n += cutColumn(x, level)
                else: break
                        
            if n < avail[level]:
                possible = False
                print('Case #' + str(j) + ': NO')
                fw.write('Case #' + str(j) + ': NO\n')
                break  
    
    if possible:
        print('Case #' + str(j) + ': YES')
        fw.write('Case #' + str(j) + ': YES\n')    


fw.close()
f.close()
                    
