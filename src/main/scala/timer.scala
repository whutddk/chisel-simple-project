package timer

import chisel3._
import chisel3.util._


class counter extends Module{
	val io = IO(new Bundle {
		val en = Input (Bool())
		val out = Output(UInt(8.W))
		val valid = Output(Bool())
	})
	
	val (a,b) = Counter(io.en, 50)
	io.out := a
	io.valid := b		

}


