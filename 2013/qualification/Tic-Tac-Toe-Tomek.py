'''
Created on Apr 12, 2013

@author: huseyngasimov
'''

def printBoard(brd):
    for x in range(4):
        print(brd[x])
        
working_dir = '/Users/huseyngasimov/git/CodeJam/CodeJam/inputoutput_files/Tic-Tac-Toe-Tomek/' 
input_filename = 'A-large.in'
output_filename = 'A-large.out'
DEBUG = False

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')


for j in range(1, int(f.readline())+1):
    board = [[]]*4    
    
    for i in range(4):
        board[i] = list(f.readline().strip())        
    
    f.readline()
    #printBoard(board)
    solved = False
    # rows
    for x in range(4):
        lead = board[x][0]
        if lead == 'T': lead = board[x][1]
        found = 0
        if lead == '.': continue 
        for y in range(4):
            if board[x][y] == lead or board[x][y] == 'T': found += 1
            else: break
        
        if found >= 4:                   
            print('Case #' + str(j) + ': ' + lead + ' won')
            fw.write('Case #' + str(j) + ': '  + lead + ' won' + '\n')
            solved = True
            break
    
    if solved:
        continue
    
    # colums 
    for x in range(4):
        lead = board[0][x]
        if lead == 'T': lead = board[1][x]
        found = 0
        if lead == '.': continue 
        for y in range(4):
            if board[y][x] == lead or board[y][x] == 'T': found += 1
            else: break
        
        if found >= 4:                   
            print('Case #' + str(j) + ': ' + lead + ' won')
            fw.write('Case #' + str(j) + ': '  + lead + ' won' + '\n')
            solved = True
            break
    
    if solved: continue
    
    # diag 1
    lead = board[0][0]
    if lead == 'T': lead = board[1][1]
    found = 0
    if not lead == '.':     
        for x in range(4):
            if board[x][x] == lead or board[x][x] == 'T': found += 1
            else: break
            
        if found >= 4:                   
            print('Case #' + str(j) + ': ' + lead + ' won')
            fw.write('Case #' + str(j) + ': '  + lead + ' won' + '\n')
            continue
                
    # diag 2
    lead = board[0][3]
    if lead == 'T': lead = board[1][2]
    found = 0
    if not lead == '.':      
        for x in range(4):
            if board[x][3-x] == lead or board[x][3-x] == 'T': found += 1
            else: break
        
        if found >= 4:                   
            print('Case #' + str(j) + ': ' + lead + ' won')
            fw.write('Case #' + str(j) + ': '  + lead + ' won' + '\n')                
            continue
        
    notCompleted = False    
    for x in range(4):
        for y in range(4):
            if board[x][y] == '.':
                notCompleted = True
                break
        if notCompleted: break
        
    if notCompleted:
        print('Case #' + str(j) + ': Game has not completed')
        fw.write('Case #' + str(j) + ': Game has not completed\n')                
        continue

    print('Case #' + str(j) + ': Draw')
    fw.write('Case #' + str(j) + ': Draw\n')                
    

    
    
fw.close()
f.close()
                    
