package regTest

import chisel3._
import chisel3.util._

class REG extends Module {
	val io = IO(new Bundle {
		val a = Input(UInt(8.W))
		val en = Input(Bool())
		val c = Output(UInt(1.W))
	})

	val reg0 = RegNext(io.a)
	val reg1 = RegNext(io.a, 0.U)
	val reg2 = RegInit(0.U(8.W))
	val reg3 = Reg(UInt(8.W))
	val reg4 = Reg(UInt(8.W))
	val reg5 = RegEnable(io.a + 1.U, 0.U, io.en)
	val reg6 = RegEnable(io.a -1.U, io.en)
	val reg7 = ShiftRegister(io.a, 3, 0.U, io.en)
	val reg8 = ShiftRegister(io.a,3, io.en)

reg2 := io.a.andR
reg3 := io.a.orR

when(reset.toBool) {
	reg4 := 0.U
} .otherwise{
	reg4 := 1.U
}



io.c := reg0(0) & reg1(0) & reg2(0) & reg3(0) & reg4(0) & reg5(0) & reg6(0) & reg7(0) & reg8(0)


}




