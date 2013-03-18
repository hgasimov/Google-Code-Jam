'''
Created on Mar 17, 2013

@author: huseyngasimov
'''
import sys

working_dir = '/Users/huseyngasimov/git/CodeJam/CodeJam/inputoutput_files/CruiseControl/' 
input_filename = 'C-small-practice.in'
output_filename = 'C-small-practice.out'
DEBUG = False

f = open(working_dir + input_filename, 'r')
fw = open(working_dir + output_filename, 'w')


def printCars(carzzz):
    print()
    for i in range(len(carzzz)):        
        print(carzzz[i][0] + ', ' + str(carzzz[i][1]) + ', ' + str(carzzz[i][2])  
              + ', frontCar = ' + str(frontCar(carzzz, i))
              + ', upperRightInterCar = ' + str(upperRightInterCar(carzzz, i))
              + ', lowerRightInterCar = ' + str(lowerRightInterCar(carzzz, i))
              + ', isLeftLaneFree = ' + str(isLeftLaneFree(carzzz, i)))
    print()

# carz is a sorted list of cars by position (ascending)
def frontCar(carz, carno):
    c = -1
    if (carno == len(carz) - 1): # with max position, i.e if this car is ahead of all cars 
        return c
    
    lane = carz[carno][0]    
    for i in range(carno+1, len(carz)):
        if (carz[i][0] == lane):
            c = i
            break
    return c

# carz is a sorted list of cars by position (ascending)
def upperRightInterCar(carz, carno):
    if (carz[carno][0] == 'R' or carno == len(carz) - 1): # if it's in the right lane or with max position, i.e if this car is ahead of all cars 
        return -1

    if (carz[carno+1][0] == 'R'):
        if (carz[carno+1][2] - carz[carno][2] < 5): # if it intersects return its number, else return -1
            return carno+1
        elif (round(carz[carno+1][2] - carz[carno][2], 6) == 5 and carz[carno+1][1] < carz[carno][1]): # it's free but if you change to the right lane, you'll crash into this car
            return -2            
        else:
            return -1
    elif (carno+2 < len(carz) and round(carz[carno+2][2] - carz[carno][2], 6) == 5 and carz[carno+2][1] < carz[carno][1]):
        return -2
    else:  
        return -1            

# carz is a sorted list of cars by position (ascending)
def lowerRightInterCar(carz, carno):
    if (carz[carno][0] == 'R' or carno == 0): # if it's in the right lane or with mix position, i.e if this car is behind of all cars 
        return -1
            
    if (carz[carno-1][0] == 'R'):
        if (carz[carno][2] - carz[carno-1][2] < 5): # if it intersects return its number, else return -1
            return carno-1
        else:
            return -1
    else:                            
        return -1

# carz is a sorted list of cars by position (ascending)    
def isLeftLaneFree(carz, carno):
    if (carz[carno][0] == 'L'):
        return False        
    if (carno > 0 and carz[carno-1][0] == 'L' and carz[carno][2] - carz[carno-1][2] < 5):
        return False    
    if (carno < len(carz)-1 and carz[carno+1][0] == 'L' and carz[carno+1][2] - carz[carno][2] < 5):
        return False        
    return True

        
        
for i in range(1, int(f.readline())+1):
    N = int(f.readline()) # number of cars
    cars = []
        
    for j in range(1, N+1):
        car = f.readline().strip().split()
        cars.append([car[0], int(car[1]), int(car[2])])

    if (N < 3):
        print('Case #' + str(i) + ': Possible')
        fw.write('Case #' + str(i) + ': Possible\n')
        continue

    cars.sort(key= lambda a: a[2], reverse=False) # don't forget to sort by position !!!    
    
    # first move the left lane cars to the right lane if possible
    for x in range(0, N): # x is car number in the list
        if (cars[x][0] == 'L'):
            urCar = upperRightInterCar(cars, x)
            lrCar = lowerRightInterCar(cars, x)
            if (urCar < 0 and lrCar < 0):
                cars[x][0] = 'R'
    
    t = 0 # time
    waitTime = [0] # time expected until the next collision
        
    laneChanged = True 
    collided = False

    while(not(collided) and len(waitTime) > 0):        
        delta_t = min(waitTime) # earliest collision
        t += delta_t        

        # calculate new positions after delta_t time
        for x in range(0, N):
            cars[x][2] += cars[x][1] * delta_t
        
        cars.sort(key= lambda a: a[2], reverse=False) # sort by position
        waitTime = []
        laneChanged = True
        
        while(laneChanged and not(collided)):
            laneChanged = False
            waitTime = []
            for x in range(0, N): # x is car number in the list
                if (cars[x][0] == 'R'):
                    fCar = frontCar(cars, x)
                    if (fCar > -1): # if there's a car in front of you
                        if (cars[fCar][2] - cars[x][2] <= 5 and cars[fCar][1] < cars[x][1]): # if you expect collision (and if it's speed is less than your speed)                            
                            if (isLeftLaneFree(cars, x)):
                                cars[x][0] = 'L' # change to the left lane
                                laneChanged = True
                            else:
                                collided = True
                                break
                        else: # if you don't expect collision now
                            if (cars[fCar][1] < cars[x][1]): # if the front car is slower than you
                                waitTime.append((cars[fCar][2] - cars[x][2] - 5) / (cars[x][1] - cars[fCar][1])) # submit expected collision time
                            #else: do nothing because the front car is faster than you or at the same speed, you're not gonna collide
                                 
                else: # car is in the left lane
                    fCar = frontCar(cars, x)
                    urCar = upperRightInterCar(cars, x)
                    lrCar = lowerRightInterCar(cars, x)
                    
                    # if it's possible to change to the right lane safely, do it now !
                    if (urCar == -1 and lrCar < 0): # if the right lane is free, change to the right lane if you don't crash into the front car in the right lane
                        cars[x][0] = 'R'                                
                        laneChanged = True                     
                    else:
                        if (fCar > -1): # if there's a car in front of you
                            if (round(cars[fCar][2] - cars[x][2], 6) <= 5 and cars[fCar][1] < cars[x][1]): # if you expect collision (and if it's speed is less than your speed)                                
                                collided = True
                                break
                            else: # if you don't expect collision now
                                if (cars[fCar][1] < cars[x][1]): # if the front car is slower than you
                                    waitTime.append((cars[fCar][2] - cars[x][2] - 5) / (cars[x][1] - cars[fCar][1])) # submit expected collision time
                                #else: do nothing because the front car is faster than you or at the same speed, you're not gonna collide
                        
                        if (urCar == -2 or urCar > -1):
                            if (cars[urCar][1] > cars[x][1]): # Case urCar == -2 cannot happen here
                                waitTime.append((5 + cars[urCar][2] - cars[x][2]) / (cars[urCar][1] - cars[x][1]))
                            elif (cars[urCar][1] < cars[x][1]): 
                                waitTime.append((5 + cars[urCar][2] - cars[x][2]) / (cars[x][1] - cars[urCar][1]))
                            #else: if the case speeds are equal, no need to do anything because passing or giveWay is not possible                         
                        elif (lrCar > -1): # which should hold if the previous 'if' statement doesn't hold                                                                                  
                            if (cars[lrCar][1] > cars[x][1]):                       
                                waitTime.append((5 - cars[lrCar][2] + cars[x][2]) / (cars[lrCar][1] - cars[x][1]))                            
                            elif (cars[lrCar][1] < cars[x][1]):                                 
                                waitTime.append((5 - cars[lrCar][2] + cars[x][2]) / (cars[x][1] - cars[lrCar][1]))
                    
            # after the 'for' statement
            if (collided):         
                t = round(t, 7)
                print('Case #' + str(i) + ': ' + str(t))
                fw.write('Case #' + str(i) + ': ' + str(t) + '\n')
                break
        
        # after the 1st 'while' statement
        
    # after the 2nd 'while' statement    
    if not(collided):
        print('Case #' + str(i) + ': Possible')
        fw.write('Case #' + str(i) + ': Possible\n')
        
    

fw.close()
f.close()