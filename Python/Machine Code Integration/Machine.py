# file: Machine.py
# author: Seungmin Lee
# date: 11/07/2014
#
# This program implements the Simple Virtual Machine (SVM).

# Define integer constants for the 11 SVM operations.
#
(LOD, STO, ADD, SUB, CMP, CPZ) = (0, 1, 2, 3, 4, 5)
(BLT, BEQ, BGT, JMP, HLT)      = (6, 7, 8, 9, 10)

# Name the general purpose registers.
#
(R0, R1, R2, R3) = (0, 1, 2, 3)

# A sample SVM program. Let M = data1[0], N = data1[1].
# Then pgm1 computes R0 = M * N.
#
data1 = [4, 5, 0, 1]
text1 = [
    (LOD, [R1, 0]),
    (LOD, [R2, 1]),
    (LOD, [R0, 2]),
    (LOD, [R3, 3]),
    (CPZ, [R2]),
    (BEQ, [3]),
    (ADD, [R0, R0, R1]),
    (SUB, [R2, R2, R3]),
    (JMP, [-5]),
    (HLT, [])
    ]
RAM1 = data1 + text1

# pgm1 is typically executed on svm with the call: svm(len(data1), RAM1).

DEBUG = True
STEP = False

# updateRegisters is a handy helper function. A call
#
# updateRegisters(value, dest, reg)
#
# produces a new set (i.e., 4-tuple) of registers having placed value
# in the specified destination register while preserving the values of
# the other registers.
#
def updateRegisters(value, dest, reg):
    if dest == 0:
        return (value, reg[1], reg[2], reg[3])
    elif dest == 1:
        return (reg[0], value, reg[2], reg[3])
    elif dest == 2:
        return (reg[0], reg[1], value, reg[3])
    else:
        return (reg[0], reg[1], reg[2], value)

# An implementation of the Simple Virtual Machine. A call svm(pc, RAM)
# executes the SVM program starting with instruction RAM[pc].
#
def svm(pc, RAM):

    # The CPU Instruction cycle.
    #
    def cycle(pc, psw, registers, RAM):

        # Fetch the next instruction from RAM.  Instructions are
        # a represented as a pair (opcode, [opnd1, opnd2, ...]).
        #
        (opcode, operands) = RAM[pc]

        # Set DEBUG above to True to get diagnostic information.
        if DEBUG:
            print '\ndbg: instr = ' + str(RAM[pc])
            print 'dbg: (pc, psw) = (' + str(pc) + ', ' + str(psw) + ')'
            print 'dbg: regs = ' + str(registers)

            if STEP:
                _ = raw_input()

        # Now dispatch on the opcode.
        #
        if opcode == ADD:        # ADD  dst, src1, src2
            dst = operands[0]
            src1 = operands[1]
            src2 = operands[2]
            v1 = registers[src1]
            v2 = registers[src2]
            newRegisters = updateRegisters(v1 + v2, dst, registers)
            cycle(pc + 1, psw, newRegisters, RAM)

        elif opcode == SUB:        # SUB  dst, src1, src2
            dst = operands[0]
            src1 = operands[1]
            src2 = operands[2]
            v1 = registers[src1]
            v2 = registers[src2]
            newRegisters = updateRegisters(v1 - v2, dst, registers)
            cycle(pc + 1, psw, newRegisters, RAM)

        elif opcode == LOD:       # LOD  dst, address
            dst = operands[0]
            address = operands[1]
            newRegisters = updateRegisters(RAM[address],dst,registers)

            cycle(pc + 1, psw, newRegisters, RAM)

        elif opcode == STO:
            src = operands[0]
            address = operands[1]
            RAM[address] = registers[src]
            cycle(pc + 1, psw, registers, RAM)

        elif opcode == CMP:
            src1 = operands[0]
            src2 = operands[1]
            v1 = registers[src1]
            v2 = registers[src2]
            newPSW = v1 - v2
            cycle(pc + 1, newPSW, registers, RAM)

        elif opcode == CPZ:
            src1 = operands[0]
            v1 = registers[src1] - 0
            cycle(pc + 1, v1, registers, RAM)

        elif opcode == BLT:
            step = operands[0]
            newPC = (pc + 1) if psw >= 0 else (pc + step + 1)
            cycle(newPC, psw, registers, RAM)

        elif opcode == BEQ:
            step = operands[0]
            newPC = (pc + step + 1) if psw == 0 else (pc + 1)
            cycle(newPC, psw, registers, RAM)

        elif opcode == BGT:
            step = operands[0]
            newPC = (pc + 1) if psw <= 0 else (pc + step + 1)
            cycle(newPC, psw, registers, RAM)

        elif opcode == JMP:
            step = operands[0]
            cycle(pc + step + 1, psw, registers, RAM)

        elif opcode == HLT:
            print "PC: " + str(pc) + " PSW: "  + str(psw) + " R0: " + str(registers[0]) + " R1: " + str(registers[1]) + " R2: " + str(registers[2]) + " R3: " + str(registers[3])
    initialPSW = 0
    initialRegisters = (0, 0, 0, 0)

    # Start the instruction cycle.
    #
    cycle(pc, initialPSW, initialRegisters, RAM)


def divide(m, n):
    adata = [m, n, 0, 1]
    atext = [
    (LOD,[R1,0]),
    (LOD,[R2,1]),
    (LOD,[R0,2]),
    (LOD,[R3,3]),
    (CPZ,[R1]),
    (BEQ,[3]),
    (SUB,[R1,R1,R2]),
    (ADD,[R0,R0,R3]),
    (JMP,[-5]),
    (HLT,[])
    ]
    aprogram = adata + atext

    return svm(len(adata), aprogram)

def factorial(n):
    adata = [n, 1, 0, 0]
    atext = [
        (LOD,[R1,0]),
        (LOD,[R2,1]),
        (LOD,[R0,2]),
        (LOD,[R3,0]),
        (CMP,[R3,R2]),
        (BEQ,[3]),
        (ADD,[R0,R0,R1]),
        (SUB,[R3,R3,R2]),
        (JMP,[-5]),
        (LOD,[R3,0]),
        (SUB,[R3,R3,R2]),
        (STO,[R3,0]),
        (SUB,[R3,R3,R2]),
        (CMP,[R3,R2]),
        (BEQ,[3]),
        (STO,[R0,3]),
        (LOD,[R1,3]),
        (JMP,[-14]),
        (HLT,[])
        ]
    aprogram = adata + atext
    return svm(len(adata),aprogram)

def testDivide():
    return divide(100,5)
def testFactorial():
    return factorial(7)
