package regFile 


import chisel3._
import chisel3.util._

class RegFile extends Module{
	val io = IO(new Bundle{
		val a = Input(UInt(8.W))
		val en = Input(Bool())
		val c = Output(UInt(1.W))	
	})

	val reg0 = RegNext(VecInit(io.a, io.a, io.a))
	val reg1 = RegNext(VecInit(io.a, io.a), VecInit(0.U, 0.U))
	val reg2 = RegInit(VecInit(0.U(8.W), 0.U(8.W)))
	val reg3 = Reg(Vec(2, UInt(8.W)))
	val reg4 = Reg(Vec(2, UInt(8.W)))
	val reg5 = RegEnable(VecInit(io.a + 1.U, io.a - 1.U), VecInit(0.U(8.W), 0.U(8.W)), io.en)
	val reg6 = RegEnable(VecInit(io.a - 1.U, io.a - 1.U), io.en)
	val reg7 = ShiftRegister(VecInit(io.a, io.a), 3, VecInit(0.U(8.W), 0.U(8.W)), io.en)
	val reg8 = ShiftRegister(VecInit(io.a, io.a), 3, io.en)


	reg2(0) := io.a.andR
	reg2(1) := io.a.andR
	reg3(0) := io.a.orR
	reg3(1) := io.a.orR

	when(reset.toBool) {
		reg4(0) := 0.U
		reg4(1) := 0.U
			
	} .otherwise{
		reg4(0) := 1.U
		reg4(1) := 1.U	
	}

	io.c := reg0(0)(0) & reg1(0)(0) & reg2(0)(0) & reg3(0)(0) & reg4(0)(0) & reg5(0)(0) & reg6(0)(0) & reg7(0)(0) & reg8(0)(0) & 
		reg0(1)(0) & reg1(1)(0) & reg2(1)(0) & reg3(1)(0) & reg4(1)(0) & reg5(1)(0) & reg6(1)(0) & reg7(1)(0) & reg8(1)(0) &
		reg0(2)(1)

}




