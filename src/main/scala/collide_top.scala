package collide_top

import chisel3._
import chisel3.util._
//import chisel3.experimental._


class collide_top extends Module{
	val io = IO ( new Bundle{
		val Link_A_X1_In = Input(SInt(16.W))
		val Link_A_X0_In = Input(SInt(16.W))
		val Link_A_Y1_In = Input(SInt(16.W))
		val Link_A_Y0_In = Input(SInt(16.W))

		val Link_A_Z1_In = Input(SInt(16.W))
		val Link_A_Z0_In = Input(SInt(16.W))

		val Link_B_X1_In = Input(SInt(16.W))
		val Link_B_X0_In = Input(SInt(16.W))

		val Link_B_Y1_In = Input(SInt(16.W))
		val Link_B_Y0_In = Input(SInt(16.W))

		val Link_B_Z1_In = Input(SInt(16.W))
		val Link_B_Z0_In = Input(SInt(16.W))
		
		val distance = Output(SInt(18.W))



		val state = Input(UInt(8.W))

	} )
	

	val Link_A_X1 = Wire(SInt(9.W))
	val Link_A_X0 = Wire(SInt(9.W))
	val Link_A_Y1 = Wire(SInt(9.W))
	val Link_A_Y0 = Wire(SInt(9.W))
	val Link_A_Z1 = Wire(SInt(9.W))
	val Link_A_Z0 = Wire(SInt(9.W))
	val Link_B_X1 = Wire(SInt(9.W))
	val Link_B_X0 = Wire(SInt(9.W))
	val Link_B_Y1 = Wire(SInt(9.W))
	val Link_B_Y0 = Wire(SInt(9.W))
	val Link_B_Z1 = Wire(SInt(9.W))
	val Link_B_Z0 = Wire(SInt(9.W))
	
	Link_A_X1 := io.Link_A_X1_In(13,5).asSInt
	Link_A_X0 := io.Link_A_X0_In(13,5).asSInt
	Link_A_Y1 := io.Link_A_Y1_In(13,5).asSInt
	Link_A_Y0 := io.Link_A_Y0_In(13,5).asSInt
	Link_A_Z1 := io.Link_A_Z1_In(13,5).asSInt
	Link_A_Z0 := io.Link_A_Z0_In(13,5).asSInt
	Link_B_X1 := io.Link_B_X1_In(13,5).asSInt
	Link_B_X0 := io.Link_B_X0_In(13,5).asSInt
	Link_B_Y1 := io.Link_B_Y1_In(13,5).asSInt
	Link_B_Y0 := io.Link_B_Y0_In(13,5).asSInt
	Link_B_Z1 := io.Link_B_Z1_In(13,5).asSInt
	Link_B_Z0 := io.Link_B_Z0_In(13,5).asSInt



	val ux = Reg(SInt(9.W))
	val uy = Reg(SInt(9.W))
	val uz = Reg(SInt(9.W))
	val vx = Reg(SInt(9.W))
	val vy = Reg(SInt(9.W))
	val vz = Reg(SInt(9.W))
	val wx = Reg(SInt(9.W))
	val wy = Reg(SInt(9.W))
	val wz = Reg(SInt(9.W))

	val uxux = Reg(SInt(18.W))
	val uyuy = Reg(SInt(18.W))
	val uzuz = Reg(SInt(18.W))

	val uxvx = Reg(SInt(18.W))
	val uyvy = Reg(SInt(18.W))
	val uzvz = Reg(SInt(18.W))

	val vxvx = Reg(SInt(18.W))
	val vyvy = Reg(SInt(18.W))
	val vzvz = Reg(SInt(18.W))

	val uxwx = Reg(SInt(18.W))
	val uywy = Reg(SInt(18.W))
	val uzwz = Reg(SInt(18.W))

	val vxwx = Reg(SInt(18.W))
	val vywy = Reg(SInt(18.W))
	val vzwz = Reg(SInt(18.W))

	val a = Reg(SInt(18.W))
	val b = Reg(SInt(18.W))
	val c = Reg(SInt(18.W))
	val d = Reg(SInt(18.W))
	val e = Reg(SInt(18.W))

	val a_ex = Reg(SInt(36.W))
	val b_ex = Reg(SInt(36.W))
	val c_ex = Reg(SInt(36.W))
	val d_ex = Reg(SInt(36.W))
	val e_ex = Reg(SInt(36.W))

	val ac = Reg(SInt(36.W))
	val bb = Reg(SInt(36.W))
	val be = Reg(SInt(36.W))
	val cd = Reg(SInt(36.W))
	val ae = Reg(SInt(36.W))
	val bd = Reg(SInt(36.W))



	val AC_SUB_BB = Wire(SInt(36.W))
	val BE_SUB_CD = Wire(SInt(36.W))
	val AE_SUB_BD = Wire(SInt(36.W))
	val E_ADD_B = Wire(SInt(36.W))




	val D_SUB_B = Reg(SInt(36.W))

	val sn_temp = Reg(SInt(36.W))
	val sd_temp = Reg(SInt(36.W))
	val tn_temp = Reg(SInt(36.W))
	val td = Reg(SInt(36.W))



	val sc = Reg(UInt(6.W))
	val tc = Reg(UInt(6.W))

	val sn_calData = Reg(UInt(41.W));
	val sd_calData = Reg(UInt(41.W));

	val tn_calData = Reg(UInt(41.W));
	val td_calData = Reg(UInt(41.W));

	val scux = Reg(SInt(15.W))
	val tcvx = Reg(SInt(15.W))
	val scuy = Reg(SInt(15.W))
	val tcvy = Reg(SInt(15.W))
	val scuz = Reg(SInt(15.W))
	val tcvz = Reg(SInt(15.W))

	val dx = Reg(SInt(9.W))
	val dy = Reg(SInt(9.W))
	val dz = Reg(SInt(9.W))

	val dxdx = Reg(SInt(18.W))
	val dydy = Reg(SInt(18.W))
	val dzdz = Reg(SInt(18.W))








	val sn_calData_move1 = Wire(UInt(41.W))
	val sn_calData_move2 = Wire(UInt(41.W))
	val SC_COMPRESS = Wire(UInt(6.W))
	val SN_CALDATA_COMPRESS = Wire(UInt(41.W))

	val tn_calData_move1 = Wire(UInt(41.W))
	val tn_calData_move2 = Wire(UInt(41.W))
	val TC_COMPRESS = Wire(UInt(6.W))
	val TN_CALDATA_COMPRESS = Wire(UInt(41.W))



	val SC_CMP_S1 = Wire(Bool())
	val SC_CMP_S2 = Wire(Bool())
	val TC_CMP_S1 = Wire(Bool())
	val TC_CMP_S2 = Wire(Bool())

	SC_CMP_S1 := sn_calData_move1 < sd_calData
	SC_CMP_S2 := sn_calData_move2 < sd_calData
	TC_CMP_S1 := tn_calData_move1 < td_calData
	TC_CMP_S2 := tn_calData_move2 < td_calData

	sn_calData_move1 := sn_calData << 1
	sn_calData_move2 := SN_CALDATA_COMPRESS << 1
	tn_calData_move1 := tn_calData << 1
	tn_calData_move2 := TN_CALDATA_COMPRESS << 1

	SN_CALDATA_COMPRESS := Mux(SC_CMP_S1 , sn_calData_move1, sn_calData_move1 - sd_calData)
	SC_COMPRESS := Mux(SC_CMP_S1 , ( sc << 1 ), ((sc << 1) | 1.U))
	TN_CALDATA_COMPRESS := Mux(TC_CMP_S1 , tn_calData_move1, tn_calData_move1 - td_calData)
	TC_COMPRESS := Mux(TC_CMP_S1 , ( tc << 1 ), ((tc << 1) | 1.U))

	val flag_parallel = Wire(Bool())
	val Foot1OutofSta = Wire(Bool())

	flag_parallel := (AC_SUB_BB === 0.S)
	Foot1OutofSta := ( BE_SUB_CD(35) === 1.U )


	val BE_SUB_CD_SSUB_AC_SUB_BB = Wire(SInt(36.W))
	val Foot1OutofEnd = Wire(Bool())

	BE_SUB_CD_SSUB_AC_SUB_BB := BE_SUB_CD - AC_SUB_BB; 
	Foot1OutofEnd := (BE_SUB_CD_SSUB_AC_SUB_BB(35) === 0.U) 


	val Foot2OutofSta = Wire(Bool())

	Foot2OutofSta := ( tn_temp(35) === 1.U )

	val TN_TEMP_SUB_TD = Wire(SInt(36.W))
	val Foot2OutofEnd = Wire(Bool())

	TN_TEMP_SUB_TD := tn_temp - td;
	Foot2OutofEnd := ( TN_TEMP_SUB_TD(35) === 0.U )


	val D_CMPB_0 = Wire(Bool())
	D_CMPB_0 := ( d(17) === 0.U )

	val D_ADD_A = Wire(SInt(18.W))
	val D_CMPS_nA = Wire(Bool())

	D_ADD_A := d + a
	D_CMPS_nA := ( D_ADD_A(17) === 1.U )


	AC_SUB_BB := ac - bb;
	BE_SUB_CD := be - cd;
	AE_SUB_BD := ae - bd;
	E_ADD_B :=  e_ex + b_ex;

	val D_SUB_B_CMPB_0 = Wire(Bool())

	D_SUB_B_CMPB_0 := ( D_SUB_B(35) === 0.U)

	val D_SUB_B_ADD_A = Wire(SInt(36.W))
	val D_SUB_B_CMPS_nA = Wire(Bool())

	D_SUB_B_ADD_A := D_SUB_B + a_ex;
	D_SUB_B_CMPS_nA := ( D_SUB_B_ADD_A(35) === 1.U )

	val distance_Reg = Reg(SInt(18.W))



when(reset.toBool)
{
	ux := 0.S
	uy := 0.S
	uz := 0.S
	vx := 0.S
	vy := 0.S
	vz := 0.S
	wx := 0.S
	wy := 0.S
	wz := 0.S


	uxux := 0.S
	uyuy := 0.S
	uzuz := 0.S
	uxvx := 0.S
	uyvy := 0.S
	uzvz := 0.S
	vxvx := 0.S
	vyvy := 0.S
	vzvz := 0.S
	uxwx := 0.S
	uywy := 0.S
	uzwz := 0.S
	vxwx := 0.S
	vywy := 0.S
	vzwz := 0.S

	a := 0.S
	b := 0.S
	c := 0.S
	d := 0.S
	e := 0.S

	a_ex := 0.S
	b_ex := 0.S
	c_ex := 0.S
	d_ex := 0.S
	e_ex := 0.S

	ac := 0.S
	bb := 0.S
	be := 0.S
	cd := 0.S
	ae := 0.S
	bd := 0.S


	D_SUB_B := 0.S

	sn_temp := 0.S
	sd_temp := 0.S
	tn_temp := 0.S
	td := 0.S

	sc := 0.U
	tc := 0.U
	sn_calData := 0.U
	sd_calData := 0.U
	td_calData := 0.U
	tn_calData := 0.U

	scux := 0.S
	tcvx := 0.S
	scuy := 0.S
	tcvy := 0.S
	scuz := 0.S
	tcvz := 0.S

	dx := 0.S
	dy := 0.S
	dz := 0.S

	dxdx := 0.S
	dydy := 0.S
	dzdz := 0.S

	distance_Reg := 0.S
}
.otherwise
{
	when( io.state === 11.U )
	{
		ux := Link_A_X1 - Link_A_X0
		uy := Link_A_Y1 - Link_A_Y0
		uz := Link_A_Z1 - Link_A_Z0

		vx := Link_B_X1 - Link_B_X0
		vy := Link_B_Y1 - Link_B_Y0
		vz := Link_B_Z1 - Link_B_Z0

		wx := Link_A_X0 - Link_B_X0
		wy := Link_A_Y0 - Link_B_Y0
		wz := Link_A_Z0 - Link_B_Z0
	}
	.elsewhen( io.state === 12.U )
	{
		uxux := ux * ux
		uyuy := uy * uy
		uzuz := uz * uz
		uxvx := ux * vx
		uyvy := uy * vy
		uzvz := uz * vz
		vxvx := vx * vx
		vyvy := vy * vy
		vzvz := vz * vz
		uxwx := ux * wx
		uywy := uy * wy
		uzwz := uz * wz
		vxwx := vx * wx
		vywy := vy * wy
		vzwz := vz * wz	
	}
	.elsewhen( io.state === 13.U )
	{
		a := uxux + uyuy + uzuz
		b := uxvx + uyvy + uzvz
		c := vxvx + vyvy + vzvz
		d := uxwx + uywy + uzwz 
		e := vxwx + vywy + vzwz

	}
	.elsewhen( io.state === 14.U )
	{
		ac := a * c
		bb := b * b
		be := b * e
		cd := c * d
		ae := a * e
		bd := b * d

		a_ex := Cat( Fill(18,a(17)) , a(17,0) ).asSInt
		b_ex := Cat( Fill(18,b(17)) , b(17,0) ).asSInt
		c_ex := Cat( Fill(18,c(17)) , c(17,0) ).asSInt
		d_ex := Cat( Fill(18,d(17)) , d(17,0) ).asSInt
		e_ex := Cat( Fill(18,e(17)) , e(17,0) ).asSInt
	}
	.elsewhen( io.state === 15.U )
	{
		D_SUB_B := d_ex - b_ex

		sn_temp := Mux( flag_parallel, 0.S,
			 			Mux( Foot1OutofSta, 0.S, 
			 				Mux( Foot1OutofEnd, AC_SUB_BB - 1.S, BE_SUB_CD)   
			 				)	
						)	


		sd_temp := Mux( flag_parallel, 100.S, AC_SUB_BB )


		tn_temp := Mux( flag_parallel, e_ex, 
						Mux( Foot1OutofSta, e_ex,
							Mux( Foot1OutofEnd, E_ADD_B, AE_SUB_BD ) 
							)
						)	


		td := 	Mux( flag_parallel, c_ex,
					Mux( Foot1OutofSta, c_ex, 
						Mux( Foot1OutofEnd, c_ex, AC_SUB_BB ) 
						)
					) 		

	}
	.elsewhen( io.state === 16.U )
	{

		sn_calData := Mux( Foot2OutofSta,
										Mux( D_CMPB_0, 0.U ,
											Mux( D_CMPS_nA, 99.U, (-d_ex).asUInt)
											),
										Mux( Foot2OutofEnd,
														Mux( D_SUB_B_CMPB_0, 0.U,
																Mux( D_SUB_B_CMPS_nA, 99.U, (-D_SUB_B).asUInt ) 
															),
															 sn_temp.asUInt)
										)

		
		sd_calData := Mux( Foot2OutofSta, 
										Mux( D_CMPB_0, (sd_temp).asUInt, 
														Mux( D_CMPS_nA, 100.U, (a_ex).asUInt ) 
											),
										Mux( Foot2OutofEnd, 
														Mux( D_SUB_B_CMPB_0, (sd_temp).asUInt,
															Mux( D_SUB_B_CMPS_nA, 100.U, (a_ex).asUInt )
														), 
														(sd_temp).asUInt
											)
							)
			
								
									
		tn_calData := Mux( Foot2OutofSta, 0.U,
							Mux( Foot2OutofEnd, (td - 1.S).asUInt, (tn_temp).asUInt) 
						)

		td_calData := (td).asUInt

		sc := 0.U;
		tc := 0.U;

	}
	.elsewhen( io.state === 17.U )
	{
		sn_calData := Mux(SC_CMP_S2, sn_calData_move2 , sn_calData_move2 - sd_calData)
		sc := Mux(SC_CMP_S2, SC_COMPRESS << 1 , ((SC_COMPRESS << 1) + 1.U))

		tn_calData := Mux(TC_CMP_S2, tn_calData_move2 , tn_calData_move2 - td_calData)
		tc := Mux(TC_CMP_S2, TC_COMPRESS << 1 , ((TC_COMPRESS << 1) + 1.U))

	}
	.elsewhen( io.state === 18.U )
	{
		sn_calData := Mux(SC_CMP_S2, sn_calData_move2, sn_calData_move2 - sd_calData)
		sc := Mux(SC_CMP_S2, SC_COMPRESS << 1, ((SC_COMPRESS << 1) + 1.U))

		tn_calData := Mux(TC_CMP_S2, tn_calData_move2, tn_calData_move2 - td_calData)
		tc := Mux(TC_CMP_S2, TC_COMPRESS << 1, ((TC_COMPRESS << 1) + 1.U))
	}
	.elsewhen( io.state === 19.U )
	{
		sn_calData := Mux(SC_CMP_S2, sn_calData_move2, sn_calData_move2 - sd_calData)
		sc := Mux(SC_CMP_S2, SC_COMPRESS << 1, ((SC_COMPRESS << 1) + 1.U))

		tn_calData := Mux(TC_CMP_S2, tn_calData_move2, tn_calData_move2 - td_calData)
		tc := Mux(TC_CMP_S2, TC_COMPRESS << 1, ((TC_COMPRESS << 1) + 1.U))
	}
	.elsewhen( io.state === 20.U )
	{
		scux := sc * ux
		tcvx := tc * vx
		scuy := sc * uy
		tcvy := tc * vy
		scuz := sc * uz
		tcvz := tc * vz
	}
	.elsewhen( io.state === 21.U )
	{
		dx := wx + scux(14,6).asSInt - tcvx(14,6).asSInt
		dy := wy + scuy(14,6).asSInt - tcvy(14,6).asSInt
		dz := wz + scuz(14,6).asSInt - tcvz(14,6).asSInt
	}
	.elsewhen( io.state === 22.U )
	{
		dxdx := dx * dx
		dydy := dy * dy
		dzdz := dz * dz
	}
	.elsewhen( io.state === 23.U )
	{
		distance_Reg := dxdx + dydy + dzdz
	}
	.elsewhen( io.state === 24.U )
	{
		distance_Reg := dxdx + dydy + dzdz
	}
	.otherwise
	{
		ux := 0.S
		uy := 0.S
		uz := 0.S
		vx := 0.S
		vy := 0.S
		vz := 0.S
		wx := 0.S
		wy := 0.S
		wz := 0.S

		uxux := 0.S
		uyuy := 0.S
		uzuz := 0.S
		uxvx := 0.S
		uyvy := 0.S
		uzvz := 0.S
		vxvx := 0.S
		vyvy := 0.S
		vzvz := 0.S
		uxwx := 0.S
		uywy := 0.S
		uzwz := 0.S
		vxwx := 0.S
		vywy := 0.S
		vzwz := 0.S

		a := 0.S
		b := 0.S
		c := 0.S
		d := 0.S
		e := 0.S

		a_ex := 0.S
		b_ex := 0.S
		c_ex := 0.S
		d_ex := 0.S
		e_ex := 0.S

		ac := 0.S
		bb := 0.S
		be := 0.S
		cd := 0.S
		ae := 0.S
		bd := 0.S


		D_SUB_B := 0.S

		sn_temp := 0.S
		sd_temp := 0.S
		tn_temp := 0.S
		td := 0.S

		sc := 0.U;
		tc := 0.U;
		sn_calData := 0.U;
		sd_calData := 0.U;
		td_calData := 0.U;

		scux := 0.S
		tcvx := 0.S
		scuy := 0.S
		tcvy := 0.S
		scuz := 0.S
		tcvz := 0.S

		dx := 0.S
		dy := 0.S
		dz := 0.S

		dxdx := 0.S
		dydy := 0.S
		dzdz := 0.S

		distance_Reg := 0.S
	}


}

io.distance := distance_Reg		


			


}


