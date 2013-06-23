'''
Created on Apr 11, 2013

@author: huseyngasimov
'''
from queue import Queue

class Bot(object):
    
    def __init__(self, me, job):
        self.targets = Queue()
        self.me = me
        self.position = 1
        pushes = job.split()
        for i in range(len(pushes)):
            if pushes[i] == me: self.targets._put(pushes[i+1])
    
    def move(self, who):
        if self.targets.empty(): return False
        target = int(self.targets.queue[0])        
        if self.position == target:            
            if self.me == who:
                self.targets.get()
                return True
        elif self.position < target:
            self.position += 1
        else: 
            self.position -= 1
        
        return False
    
working_dir = '/Users/huseyngasimov/git/CodeJam/CodeJam/inputoutput_files/BotTrust/' 
input_filename = 'A-large-practice.in'
output_filename = 'A-large-practice.out'
DEBUG = False

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')


for j in range(1, int(f.readline())+1):
    line = f.readline().strip()
    O = Bot('O', line)
    B = Bot('B', line)
            
    pushes = line.split(' ')         
    N = int(pushes[0])
    time = 0
    for i in range(N):
        who = pushes[1 + i*2]
        complete = False
        while not complete:
            complete = O.move(who) or complete
            complete = B.move(who) or complete
            time += 1
        
    
                
    print('Case #' + str(j) + ': ' + str(time))
    fw.write('Case #' + str(j) + ': '  + str(time) + '\n')    


fw.close()
f.close()
                    
