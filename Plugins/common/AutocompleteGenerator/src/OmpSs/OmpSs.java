package OmpSs;

import org.json.*;



import java.io.FileWriter;
import java.io.IOException;
public class OmpSs {
	static public final OmpSsElement pragma = new OmpSsElement(OmpSsType.ppragma);


	static void addArrayToNode(OmpSsElement node, OmpSsElement[] ...ar) {
		
		for(OmpSsElement[] T : ar)
			for (int i = 0; i < T.length; ++i) {
				node.add(T[i]);	
			}
		}

	
	
	
	
	static OmpSsElement generateTaskElement() {
		
		
		
		return new OmpSsElement(OmpSsType.ptask)
				.add(OmpSsType.pshared, true)
				.add(OmpSsType.preduction, true)
				.add(OmpSsType.ppriority)
				.add(OmpSsType.pif)
				.add(OmpSsType.pfinal)
				.add(OmpSsType.plabel)
				.add(OmpSsType.ptied)
				.add(OmpSsType.pfirstprivate, true)
				.add(OmpSsType.pprivate, true)
				.add(OmpSsType.pin, true)
				.add(OmpSsType.pout, true)
				.add(OmpSsType.pinout, true)
				.add(OmpSsType.pdepend, true);
	}



	
	static OmpSsElement generateTargetElement() {
		return new OmpSsElement(OmpSsType.ptarget)
			.add(OmpSsType.pdevice)
			.add(OmpSsType.pcopyin)
			.add(OmpSsType.pcopyout)
			.add(OmpSsType.pcopyinout)
			.add(OmpSsType.pcopydeps)
			.add(OmpSsType.pimplements)
			.add(OmpSsType.pnuminstances)
			.add(OmpSsType.ponto);
	}

	

	
	static OmpSsElement generateForElement() {
		
		return new OmpSsElement(OmpSsType.pfor)
		.add(OmpSsType.pschedule)
		.add(OmpSsType.pnowait);
	}
	
	static OmpSsElement generateTaskwaitConstruct() {
		
		return new OmpSsElement(OmpSsType.ptaskwait)
		.add(OmpSsType.pon);
	}
	
	static OmpSsElement generateDeclareConstruct() {
		
		return new OmpSsElement(OmpSsType.pdeclare)

		.add(OmpSsType.preduction)
		.addLastChild(OmpSsType.pinitializer);
	}
	
	
	
	
	
	static OmpSsElement HLSAllocation() {
		
		return new OmpSsElement(OmpSsType.pallocation)

		.add(OmpSsType.pinstances)
		.add(OmpSsType.plimit)
		.add(OmpSsType.pfunction)
		.add(OmpSsType.poperation)
		.add(OmpSsType.pcore);
		
	}
	
	
	
	static OmpSsElement HLSAggregate() {
		
		
		return new OmpSsElement(OmpSsType.paggregate).add(OmpSsType.pvariable);
	}
	
	static OmpSsElement HLSBindOp() {
		
		
		return new OmpSsElement(OmpSsType.bindop).add(OmpSsType.pvariable).add(OmpSsType.platency).add(OmpSsType.impl).add(OmpSsType.pop);
	}
	
	
	static OmpSsElement HLSExpressionBalance() {
		
		return new OmpSsElement(OmpSsType.pexpressionbalance)
		.add(OmpSsType.poff);

		
	}
	
	
	
	
	static OmpSsElement HLSLatency() {
		
		return new OmpSsElement(OmpSsType.platency)

		.add(OmpSsType.pmin)
		.add(OmpSsType.pmax);

		
	}
	
	
	
	static OmpSsElement HLSreset() {
		
		return new OmpSsElement(OmpSsType.preset)

		.add(OmpSsType.pvariable)
		.add(OmpSsType.poff);

		
	}
	
	static OmpSsElement HLSResource() {
		
		return new OmpSsElement(OmpSsType.presource)

		.add(OmpSsType.pvariable)
		.add(OmpSsType.pcore)
		.add(OmpSsType.platency);
	}
	
	static OmpSsElement HLSStable() {
		
		return new OmpSsElement(OmpSsType.pstable)
				.add(OmpSsType.pvariable);

	}
	
	static OmpSsElement HLSInline() {
		
		return new OmpSsElement(OmpSsType.pinline)
				.add(OmpSsType.poff)
				.add(OmpSsType.pregion)
				.add(OmpSsType.precursive);

	}
	
	static OmpSsElement HLSFunctioninstantiate() {
		
	 return new OmpSsElement(OmpSsType.pfunction_instantiate).add(OmpSsType.pvariable);		
	}
	

	static OmpSsElement HLSInterface() {
		
		OmpSsElement iface = new OmpSsElement(OmpSsType.pinterface);
		OmpSsElement modes[] = {
				new OmpSsElement(OmpSsType.papnone)     ,
				new OmpSsElement(OmpSsType.papstable)   ,
				new OmpSsElement(OmpSsType.papvld)      ,
				new OmpSsElement(OmpSsType.papack)      ,
				new OmpSsElement(OmpSsType.paphs)       ,
				new OmpSsElement(OmpSsType.papolvd)    ,
				new OmpSsElement(OmpSsType.papfifo)     ,
				new OmpSsElement(OmpSsType.papbus)      ,
				new OmpSsElement(OmpSsType.papmemory)   ,
				new OmpSsElement(OmpSsType.pbram)       ,
				new OmpSsElement(OmpSsType.paxis)       ,
				new OmpSsElement(OmpSsType.psaxilite)   ,
				new OmpSsElement(OmpSsType.pmaxi)       ,
				new OmpSsElement(OmpSsType.papctrlnone) ,
				new OmpSsElement(OmpSsType.papctrlhs)   ,
				new OmpSsElement(OmpSsType.papctrlchain)};
		
		
		OmpSsElement itm[] = {
				new OmpSsElement(OmpSsType.pport)     ,
				new OmpSsElement(OmpSsType.pbundle)   ,
				new OmpSsElement(OmpSsType.pregister)      ,
				new OmpSsElement(OmpSsType.pregister_mode)      ,
				new OmpSsElement(OmpSsType.pdepth)       ,
				new OmpSsElement(OmpSsType.poffset)    ,
				new OmpSsElement(OmpSsType.pclock)     ,
				new OmpSsElement(OmpSsType.pname)      ,
				new OmpSsElement(OmpSsType.pnum_read_outstanding)   ,
				new OmpSsElement(OmpSsType.pnum_write_outstanding)       ,
				new OmpSsElement(OmpSsType.pmax_read_burst_lenght)       ,
				new OmpSsElement(OmpSsType.pmax_write_burst_lenght)   ,
				new OmpSsElement(OmpSsType.pmaxi)       ,
				new OmpSsElement(OmpSsType.papctrlnone) ,
				new OmpSsElement(OmpSsType.papctrlhs)   ,
				new OmpSsElement(OmpSsType.papctrlchain)};
		
		
		
		for(OmpSsElement elem : modes)
		{
			elem.add(itm);
		}
		iface.add(modes);		
		
		return iface;
	}
	
	
	static OmpSsElement HLSDataflow() {
		
		return new OmpSsElement(OmpSsType.pdataflow);
	}	
	
	
	static OmpSsElement HLSDependence() {
		
		OmpSsElement rst = new OmpSsElement(OmpSsType.pdependence)
		.add(OmpSsType.pvariable)
		.add(OmpSsType.pdistance)
		.add(OmpSsType.parray)
		.add(OmpSsType.ppointer);	
		
		
		OmpSsElement pintra = new OmpSsElement(OmpSsType.pintra);
		OmpSsElement pinter = new OmpSsElement(OmpSsType.pinter);

		OmpSsElement RAW = new OmpSsElement(OmpSsType.praw);
		OmpSsElement WAR = new OmpSsElement(OmpSsType.pwar);
		OmpSsElement WAW = new OmpSsElement(OmpSsType.pwaw);

		
		OmpSsElement ptrue = new OmpSsElement(OmpSsType.ptrue);
		OmpSsElement pfalse = new OmpSsElement(OmpSsType.pfalse);

		
		rst.add(pintra, pinter);
		pintra.add(RAW,WAR,WAW, ptrue, pfalse);
		pinter.add(RAW,WAR,WAW, ptrue, pfalse);
	
		
		return rst;
	}

	
	static OmpSsElement HLSStream() {
		
		return new OmpSsElement(OmpSsType.pstream)
		.add(OmpSsType.pvariable)
		.add(OmpSsType.pdepth)
		.add(OmpSsType.pdim)
		.add(OmpSsType.poff);
		
	}

	static OmpSsElement HLSUnroll() {
		
		return new OmpSsElement(OmpSsType.punroll)
		.add(OmpSsType.pfactor)
		.add(OmpSsType.pregion)
		.add(OmpSsType.pskip_exit_check);
		
	}
	
	static OmpSsElement HLSPipeline() {
		
		return new OmpSsElement(OmpSsType.ppipeline)
				.add(OmpSsType.pII)
				.add(OmpSsType.penable_flush)
				.add(OmpSsType.prewind);
	}
	
	static OmpSsElement HLSOcurrence() {
		
		OmpSsElement rst = new OmpSsElement(OmpSsType.pocurrence);
		rst.add(OmpSsType.pcycle);

		
		return rst;
	}
	
	static OmpSsElement HLSLoopFlatten() {
		
		return  new OmpSsElement(OmpSsType.ploopflatten)
		.add(OmpSsType.poff);

	}
	static OmpSsElement HLSLoopMerge() {
			
			 return new OmpSsElement(OmpSsType.ploopmerge)
			.add(OmpSsType.pforce);
	
	}

static OmpSsElement HLSLoopTripcount() {
	
	return new OmpSsElement(OmpSsType.plooptripcount)
	.add(OmpSsType.pmin)
	.add(OmpSsType.pmax)
	.add(OmpSsType.pavg);

}
	
static OmpSsElement HLSArrayMap() {
	
	return new OmpSsElement(OmpSsType.parraymap)
			.add(OmpSsType.pvariable)
			.addLastChild(OmpSsType.pinstance)
			.addLastChild(OmpSsType.pvertical)
			.addLastChild(OmpSsType.phorizontal)
			.add(OmpSsType.poffset);

}
	
static OmpSsElement HLSArrayPartition() {
	
	return  new OmpSsElement(OmpSsType.parraypartition)
	.add(OmpSsType.pvariable)
	
	.add(OmpSsType.pcyclic)
	.add(OmpSsType.pblock)
	.add(OmpSsType.pcomplete)
	
	.add(OmpSsType.pfactor)
	.add(OmpSsType.pdim);

	
}
	
static OmpSsElement HLSArrayReshape() {
	
	 return new OmpSsElement(OmpSsType.parray_reshape)
		.add(OmpSsType.pvariable)
		.add(OmpSsType.pblock)
		.add(OmpSsType.pcomplete)
		.add(OmpSsType.pcyclic)
		.add(OmpSsType.pfactor)
		.add(OmpSsType.pdim);

	

}
	
	
static OmpSsElement HLSDataPack() {
	
	return new OmpSsElement(OmpSsType.pdatapack)
		.add(OmpSsType.pvariable)
		.add(OmpSsType.pinstance)
		.add(OmpSsType.pstructlevel)
		.add(OmpSsType.pfieldlevel);

	
}


static OmpSsElement HLSBindStorage() {
	
	return new OmpSsElement(OmpSsType.pbindstorage)
		.add(OmpSsType.pvariable)
		.add(OmpSsType.ptypefifo)
		.add(OmpSsType.ptype)
		.add(OmpSsType.ptype_ram_1p)
		.add(OmpSsType.ptype_ram_1wnr)
		.add(OmpSsType.pytype_ram_2p)
		.add(OmpSsType.ptype_ram_s2p)
		.add(OmpSsType.ptype_ram_t2p)
		.add(OmpSsType.ptype_rom_1p)
		.add(OmpSsType.ptype_rom_2p)
		.add(OmpSsType.ptype_rom_np)
		
		.add(OmpSsType.impl)
		.add(OmpSsType.impl_bram)
		.add(OmpSsType.impl_lutram)
		.add(OmpSsType.impl_uram)
		.add(OmpSsType.impl_srl)
		
		.add(OmpSsType.platency);

	
}

static OmpSsElement HLSTop() {
	
	return new OmpSsElement(OmpSsType.ptop)
		.add(OmpSsType.pname);

	
}
static OmpSsElement HLSShared() {
	
	return new OmpSsElement(OmpSsType.ptop).add(OmpSsType.pvariable);

	
}

static OmpSsElement HLSDepdendence() {
	
	return new OmpSsElement(OmpSsType.pdependence)
		.add(OmpSsType.pvariable)
			.addLastChild(OmpSsType.pinter)
			.addLastChild(OmpSsType.pintra)
			.addLastChild(OmpSsType.parray)
			.addLastChild(OmpSsType.ppointer)
			.addLastChild(OmpSsType.praw)
			.addLastChild(OmpSsType.pwar)
			.addLastChild(OmpSsType.pwaw)

		.add(OmpSsType.pdistance)
		.add(OmpSsType.ptrue)
		.add(OmpSsType.pfalse);

	
}

	static {
		// omp
		OmpSsElement omp = new OmpSsElement(OmpSsType.pomp);
		pragma.add(omp);


		// #pragma omp task
		omp.add(
				generateTaskElement().add(OmpSsType.none),
				generateTargetElement().add(OmpSsType.none), 
				generateForElement().add(OmpSsType.none), 
				generateTaskwaitConstruct().add(OmpSsType.none), 
				new OmpSsElement(OmpSsType.ptaskyield).add(OmpSsType.none),
				new OmpSsElement(OmpSsType.patomic).add(OmpSsType.none),
				new OmpSsElement(OmpSsType.pcritical).add(OmpSsType.none),
				generateDeclareConstruct().add(OmpSsType.none)
				);
		
		
		OmpSsElement HLS = new OmpSsElement(OmpSsType.phls);
		pragma.add(HLS);
		
		
		HLS.add(
				HLSAggregate(),
				HLSBindOp(),
				HLSBindStorage(),
				HLSTop(),
				HLSShared(),
				HLSDepdendence(),
				
				HLSAllocation(),
				HLSExpressionBalance(),
				HLSLatency(),
				HLSreset(),
				HLSResource(),
				HLSStable(),
				HLSInline(),
				HLSFunctioninstantiate(),
				HLSInterface(),
				HLSLatency(),
				HLSDataflow(),
				HLSDependence(),
				HLSStream(),
				HLSUnroll(),
				HLSPipeline(),
				HLSOcurrence(),
				HLSLoopFlatten(),
				HLSLoopTripcount(),
				HLSArrayMap(),
				HLSArrayPartition(),
				HLSArrayReshape(),
				HLSDataPack()
				
				);
		
		
		//HLS.add();
		


	}

	public enum OmpSsType {
		ppragma("#pragma"), pomp("omp"), pbind("bind"), phint("hint"),
		pseq_cst("seq_cst"), pacq_rel("acq_rel"), pacquire("acquire"), prelaxed("relaxed"),

		psingle("single"), pcopyprivate("copyprivate"), pnowait("nowait"), pto("to"), plink("link"), phost("host"),
		// tasks
		ptask("task"), ptaskwait("taskwait"), pon("on"), prelease("release"), pwait("wait"), pweakwait("weakwait"),
		plabel("label"), pprivate("private"), pfirstprivate("firstprivate"), plastprivate("lastprivate"),
		pshared("shared"), pcritical("critical"), pin("in"), pinout("inout"), pout("out"), pconcurrent("concurrent"),
		pcommutative("commutative"), pdepend("depend"), pfinal("final"), pcost("cost"), ppriority("priority"),
		pif("if"), ptaskyield("taskyield"), pbarrier("barrier"), pcancel("cancel"), ptaskgroup("taskgroup"),
		pdevice_type("device_type"),

		// endtasks
		pmatch("match"), puse_device_ptr("use_device_ptr"),
		// parallel
		pparallel("parallel"), pdistribute("distribute"), pteams("teams"), pfor("for"), pmaster("master"),
		ptaskloop("taskloop"), preduction("reduction"), pproc_bind("proc_bind"),
		pnum_threads("num_threads"), pdefault("default"), pallocate("allocate"), ploop("loop"),
		
		pcopyout("copy_out"), pcopyinout("copy_inout"), pcopydeps("copy_deps"), pimplements("implements"), pcopyin("copy_in"),
		
		pnuminstances("num_instances"),
		ponto("onto"),
		// end parallel
		
		// simd
		psimd("simd"), psafelen("safelen"), psimdlen("simdlen"), paligned("aligned"), pnontemporal("nontemporal"),
		pdo("do"), pfrom("from"), pdata("data"), penter("enter"),

		// end simd
		pupdate("update"), pdestroy("destroy"),
		// metadirectives
		pbegin("begin"), pend("end"), pmetadirective("metadirective"),
		// end metadirectives

		// variant
		pdeclare("declare"), pvariant("variant"),
		// end variant

		pread("read"), pwrite("write"), pcapture("capture"),
		pthreads("threads"),
		// requires
		prequires("requires"), preverse_offload("reverse_offload"), punified_address("unified_address"),
		punified_shared_memory("unified_shared_memory"), patomic_default_mem_order("atomic_default_mem_order"),
		pdynamic_allocators("dynamic_allocators"),
		// end requires

		// worksharing
		psections("sections"), psection("section"),

		plinear("linear"), porder("order"),

		pcollapse("collapse"), pordered("ordered"),

		// end worksharing

		// team construct

		pnum_teams("num_teams"), pthread_limit("thread_limit"),

		pdist_schedule("dist_schedule"),

		pscan("scan"), pinclusive("inclusive"), pexclusive("exclusive"),

		paffinity("affinity"), pdetach("detach"), puntied("untied"), pmergeable("mergeable"),

		puniform("uniform"), pinbranch("inbranch"), pnotinbranch("notinbranch"),

		pin_reduction("in_reduction"),

		pgrainsize("grainsize"), pnum_tasks("num_tasks"), pnogroup("nogroup"),

		// end team construct

		pflush("flush"), patomic("atomic"), pdepobj("depobj"), pcancellation("cancellation"), ppoint("point"),
		pthreadprivate("threadprivate"), pmapper("mapper"),
		// target

		ptarget("target"), pis_device_ptr("is_device_ptr"), pmap("map"), pdefaultmap("defaultmap"),
		puses_allocators("uses_allocators"), pdevice("device"),

		pschedule("schedule"),
		// end target
		
		
		//devicesnames
		pfpga("fpga"),
		ptied("tied"),
		pinitializer("initializer"),
		
		
		/*xilinx*/
		phls("HLS"),
		pallocation("allocation"),
		pinstances("instances"), plimit("limit"), pfunction("function"), poperation("operation"), pcore("core"),
		paggregate("aggregate"),
		poff("off"),
		pdim("dim"),
		pregion("region"),
		pvariable("variable"),
		precursive("recursive"),
		pmin("min"),
		pmax("max"),
		pavg("avg"),
		pbindstorage("bind_storage"),
		pskip_exit_check("skip_exit_check"),
		pfactor("factor"),
		
		pintra("intra"), pinter("inter"), praw("RAW"), pwar("WAR"),pwaw("WAW"), ptrue("true"), pfalse("false"),pdistance("distance"),parray("array"), ppointer("pointer"),
		
		
	
		
		pcyclic("cyclic"),
		pblock("block"),
		pcomplete("complete"),
	
		
		
		/*pmode*/
		papnone("ap_none"), papstable("ap_stable"), papvld("ap_vld"), papack("ap_ack"), paphs("ap_hs"), papolvd("ap_olvd"),
		papfifo("ap_fifo"), papbus("ap_bus"), papmemory("ap_memory"), pbram("bram"), paxis("axis"), psaxilite("s_axilite"),
		pmaxi("m_axi"), papctrlnone("ap_ctrl_none"), papctrlhs("ap_ctrl_hs"), papctrlchain("ap_ctrl_chain"), pport("port"),
		pbundle("bundle"),
		pregister("pregister"),pregister_mode("pregister_mode"),
		pdepth("depth"),
		poffset("offset"),
		pinstance("instance"),
		pclock("clock"),
	
		
		pnum_read_outstanding("num_read_outstanding"),
		pnum_write_outstanding("num_write_outstanding"),
		pmax_read_burst_lenght("max_read_burst_lenght"),
		pmax_write_burst_lenght("max_write_burst_lenght"),
		pname("name"),
		ptop("top"),
		
		///
		bindop("bind_op"),
		impl("impl"),
		pop("op"),
		pII("II"),
		penable_flush("enable_flush"),
		prewind("rewind"),
		phorizontal("horizontal"),
		pvertical("vertical"),
		
		pstructlevel("struct_level"),
		pfieldlevel("field_level"),
		
		pcycle("cycle"),
		pocurrence("ocurrence"),
		pexpressionbalance("expression_balance"),
		platency("latency"),
		preset("reset"),
		presource("resource"),
		pstable("stable"),
		pinline("inline"),
		pfunction_instantiate("function_instantiate"),
		pinterface("interface"),
		pdataflow("dataflow"),
		pstream("stream"),
		ppipeline("pipeline"),
		pconcurrence("concurrence"),
		punroll("unroll"),
		pdependence("dependence"),
		ploopflatten("loop_flatten"),
		ploopmerge("loop_merge"),
		plooptripcount("loop_tripcount"),
		parraymap("array_map"),
		parraypartition("array_partition"),
		parray_reshape("array_reshape"),
		pdatapack("data_pack"),
		
		pforce("force"),
		
		
		//ptypes
		
		ptypefifo("type=fifo"),
		ptype("type"),
		ptype_ram_1p("type=ram_1p"),
		ptype_ram_1wnr("type=ram_1wnr"),
		pytype_ram_2p("type=ram_2p"),
		ptype_ram_s2p("type=ram_s2p"),
		ptype_ram_t2p("type=ram_t2p"),
		ptype_rom_1p("type=rom_1p"),
		ptype_rom_2p("type=rom_2p"),
		ptype_rom_np("type=rom_np"),
		impl_bram("impl=bram"),
		impl_lutram("impl=lutram"),
		impl_uram("impl=uram"),
		impl_srl("impl=srl"),
		none("");

		private String token;
		private boolean needExpr;
		public int offset;

		OmpSsType(String text) {
			this.token = text;
			switch (token) {

				
			case "#pragma":
			case "omp":
			case "task":
			case "taskwait":
			case "weakwait":
			case "wait":
			case "simd":
			case "parallel":
			case "taskloop":
			case "taskyield":
			case "target":
			case "update":
			case "loop":
			case "sections":
			case "section":
			case "distribute":
			case "teams":
			case "critical":
			case "barrier":
			case "taskgroup":
			case "atomic":
			case "flush":
			case "ordered":
			case "cancel":
			case "cancellation":
			case "point":
			case "declare":
			case "mapper":			
			case "final":
			case "untied":
			case "mergeable":
			case "nogroup":
			case "for":
			case "nowait":
			case "capture":
			case "read":
			case "write":
			case "acq_rel":
			case "acquire":
			case "hint":
			case "relaxed":
			case "release":
			case "seq_cst":
			case "master":
			case "threads":
			case "requires":
			case "single":
			case "scan":
			case "copy_deps":
			case "HLS":
			case "reset":
			case "off":
			case "pipeline":
			case "enable_flush":
			case "rewind":
			case "allocation":
			case "expression_balance":
			case "latency":
			case "resource":
			case "stable":
			case "inline":
			case "function_instantiate":
			case "interface":
			case "dataflow":
			case "stream":
			case "occurrence":
			case "unroll":
			case "dependence":
			case "loop_flatten":
			case "loop_merge":
			case "loop_tripcount":
			case "array_map":
			case "array_partition":
			case "array_reshape":
			case "data_pack":	
			case "min":
			case "max":
			case "avg":
			case "region":
			case "recursive":
			case "ap_none":
			case "ap_stable":
			case "ap_vld":
			case "ap_ack":
			case "ap_hs":
			case "ap_olvd":
			case "ap_fifo":
			case "ap_bus":
			case "ap_memory":
			case "ap_ctrlnone":
			case "ap_ctrlhs":
			case "ap_ctrlchain":
			case "bram":
			case "axis":
			case "saxilite":
			case "maxi":
			case "max_read_burst_lenght":
			case "um_write_outstanding":
			case "num_read_outstanding":
			case "max_write_burst_lenght":
			case "depth":
			case "intra":
			case "inter":
			case "true":
			case "false":
			case "WAR":
			case "WAW":
			case "RAW":
			case "array":
			case "pointer":
			case "pdim":
			case "II":
			case "factor":
			case "skip_exit_check":
			case "ocurrence":
			case "cycle":
			case "force":
			case "instance":
			case "vertical":
			case "horizontal":
			case "cyclic":
			case "complete":
			case "block":
			case "struct_level":
			case "field_level":
			case "bind_op":
			case "type=fifo" :
			case "type=ram_1p" :
			case "type=ram_1wnr" :
			case "type=ram_2p" :
			case "type=ram_s2p" :
			case "type=ram_t2p" :
			case "type=rom_1p" :
			case "type=rom_2p" :
			case "type=rom_np" :
			case "impl=bram" :
			case "impl=lutram" :
			case "impl=uram" :
			case "impl=srl" :
			case "bind_storage":
			case "top":
				needExpr = false;
				break;
			default:
				needExpr = true;
			}
		}

		public int getOffset() {
			switch (this) {
			case ppragma:
			case pomp:
			case ptask:
			case ptaskwait:
			case pweakwait:
			case pwait:
			case pfinal:
				return 0;
			case plabel:
				return 2;
			default:
				return 1;
			}
		}

		public String getDisplayText() {
			return this.token;
		}

		public String getText() {

			switch (this) {
			case ppragma:
			case pomp:
			case ptask:
			case ptaskwait:
			case pweakwait:
			case pwait:
			case psimd:
			case pparallel:
			case ptaskloop:
			case ptaskyield:
			case ptarget:
			case pupdate:
			case ploop:
			case psections:
			case psection:
			case pdistribute:
			case pteams:
			case pcritical:
			case pbarrier:
			case ptaskgroup:
			case patomic:
			case pflush:
			case pordered:
			case pcancel:
			case pcancellation:
			case ppoint:
			case pdeclare:
			case pmapper:
			case pfor:
			case pnowait:
			case pfinal:
			case pcapture:
			case pread:
			case pwrite:

			case pacq_rel:
			case pacquire:
			case phint:
			case prelaxed:
			case prelease:
			case pseq_cst:
			case pnogroup:
			case pmergeable:
			case puntied:
			case pmaster:

			case prequires:
			case pthreads:
			case psingle:
			case pscan:
				return this.token + " ";
			case plabel:
				return this.token + "(\"\")";
			default:
				return this.token + "()";
			}
		}
		
		public boolean isRepeatable()
		{
			return false;
			
		}
		
	}

	
	
	void recursivelyRunJson(OmpSsElement node, JSONObject currentNode)
	{
			JSONArray defaults	= new JSONArray();
				
			for(OmpSsElement a : node.getNextStates())
			{
				if(a.getNextStates().size()==0)
				{
					defaults.put(a.getValue().getDisplayText());
				}
				else 
				{
					JSONObject special = new JSONObject();
					recursivelyRunJson(a, special);
					currentNode.put(a.getValue().getDisplayText(), special);
				}	
			}
			
			currentNode.put("defaults", defaults);
		
	}
	
	public void generateTreeJson()
	{
		JSONObject first = new JSONObject();
		recursivelyRunJson(pragma,first);
		JSONObject pragma = new JSONObject();
		pragma.put("#pragma", first);
		try {
			FileWriter a = new FileWriter("OmpSs/tree.json");
			a.write(pragma.toString(4));
			a.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void generateDirectivesInfo()
	{
		JSONObject base = new JSONObject();
		for(OmpSsType t : OmpSsType.values())
		{
			JSONObject type = new JSONObject();
			base.put(t.getDisplayText(), type);
			type.put("repeatable", t.isRepeatable());
			type.put("expression", t.needExpr);

		}
		
		try {
			FileWriter a = new FileWriter("OmpSs/tokens.json");
			a.write(base.toString(4));
			a.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}