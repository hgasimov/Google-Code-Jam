'''
Created on Mar 15, 2013

@author: huseyngasimov
'''
from queue import PriorityQueue

working_dir = '/Users/huseyngasimov/git/CodeJam/CodeJam/inputoutput_files/KingdomRush/' 
input_filename = 'B-large-practice.in'
output_filename = 'B-large-practice.out'
DEBUG = False

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')

for i in range(1, int(f.readline())+1):
    N = int(f.readline()) # number of levels    
    p2 = PriorityQueue(N) # stars needed for levels to be completed with 2 stars
    ind = [[0, 0, 0]] # index, ind[0] is not used   
    for j in range(1, N+1):
        line = f.readline().strip().split()
        aj, bj = int(line[0]), int(line[1])        
        p2.put((bj, bj, j)) # (priority, stars, level)
        ind.append([aj, bj, 0]) # 3rd column can be:
                                # 0 - level not completed
                                # 1 - level completed with 1 star
                                # 2 - level completed with 2 stars
    
    mov = 0 # number of movements, i.e number of times levels completed
    curStars = 0 # current stars
    while (not p2.empty()):
        _, star2, level = p2.queue[0]
        if (curStars >= star2): # if curStars is enough to complete some level with 2 stars, do it and get 1 or 2 stars
            p2.get()
            if (ind[level][2] == 1): curStars += 1 # get 1 star if you completed this level 1 star before
            else: curStars += 2 # get 2 stars if you complete it first time
            mov += 1
            ind[level][2] = 2 # level completed with 2 stars
            #ind.pop(level) 
            if DEBUG: print('L2: ' + str(level))
        else:   
            level = -1
            if DEBUG: print(len(ind))
            for m in range(1, len(ind)):
                if (ind[m][2] == 0 and ind[m][0] <= curStars):
                    if (level == -1 or ind[m][1] > ind[level][1]):
                        level = m

                        
            if DEBUG: print('L1 : ' + str(level))
            if (level == -1):
                if DEBUG: print(ind)
                mov = -1
                break
            else:
                curStars += 1
                mov += 1
                ind[level][2] = 1
                        
            
            
    if (mov == -1):
        print('Case #' + str(i) + ': Too Bad')
        fw.write('Case #' + str(i) + ': Too Bad\n')
    else:
        print('Case #' + str(i) + ': ' + str(mov))
        fw.write('Case #' + str(i) + ': ' + str(mov) + '\n')

    




fw.close()
f.close()

