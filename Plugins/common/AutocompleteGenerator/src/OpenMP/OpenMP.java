package OpenMP;
import org.json.*;


import java.io.FileWriter;
import java.io.IOException;
public class OpenMP {
	static public final OpenMPElement pragma = new OpenMPElement(OpenMPType.ppragma);


	static void addArrayToNode(OpenMPElement node, OpenMPElement[] ar) {
			for (int i = 0; i < ar.length; ++i) {
				node.add(ar[i]);	
			}
		}

	static OpenMPElement generateTaskElement() {
		OpenMPElement task = new OpenMPElement(OpenMPType.ptask);

		OpenMPElement untied = new OpenMPElement(OpenMPType.puntied);
		OpenMPElement mergeable = new OpenMPElement(OpenMPType.pmergeable);
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend, true);
		OpenMPElement priority = new OpenMPElement(OpenMPType.ppriority);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement paffinity = new OpenMPElement(OpenMPType.paffinity);
		OpenMPElement pdetach = new OpenMPElement(OpenMPType.pdetach);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement pfinal = new OpenMPElement(OpenMPType.pfinal);

		OpenMPElement ar[] = { untied, mergeable, pprivate, firstprivate, shared, in_reduction, depend, priority, allocate,
				paffinity, pdetach, pdefault, pif, pfinal };

		addArrayToNode(task, ar);

		return task;
	}

	static OpenMPElement generateTaskwaitElement() {
		OpenMPElement taskwait = new OpenMPElement(OpenMPType.ptaskwait);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend, true);

		taskwait.add(depend);
		//depend.add(depend);

		return taskwait;
	}

	static OpenMPElement generateReleaseElement() {
		OpenMPElement prelease = new OpenMPElement(OpenMPType.prelease);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend, true);

		prelease.add(depend);
		//depend.add(depend);

		return prelease;
	}

	static OpenMPElement generateBeginMetadirectiveElement() {
		OpenMPElement begin = new OpenMPElement(OpenMPType.pbegin);
		begin.add(new OpenMPElement(OpenMPType.pmetadirective));
		return begin;
	}

	
	static OpenMPElement extensionEndDeclare()
	{
		OpenMPElement declare = new OpenMPElement(OpenMPType.pdeclare);
		
		
		OpenMPElement to = new OpenMPElement(OpenMPType.pto);
		OpenMPElement link = new OpenMPElement(OpenMPType.plink);
		OpenMPElement device_type = new OpenMPElement(OpenMPType.pdevice_type);
		
		OpenMPElement ar[] = {to, link, device_type};
		
		addArrayToNode(declare, ar);
		
		return declare;
		
	}
	static OpenMPElement generateEndElement() {
		OpenMPElement end = new OpenMPElement(OpenMPType.pend);
		end.add(new OpenMPElement(OpenMPType.pmetadirective));
		
		end.add(extensionEndDeclare());
		return end;
	}

	static OpenMPElement generateSectionsElement() {
		OpenMPElement sections = new OpenMPElement(OpenMPType.psections);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);

		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);

		OpenMPElement ar[] = { pprivate, firstprivate, lastprivate, reduction, allocate, nowait };

		addArrayToNode(sections, ar);

		return sections;
	}

	static OpenMPElement generateSingleElement() {
		OpenMPElement single = new OpenMPElement(OpenMPType.psingle);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement copyprivate = new OpenMPElement(OpenMPType.pcopyprivate);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);

		OpenMPElement ar[] = { pprivate, firstprivate, copyprivate, allocate, nowait };

		addArrayToNode(single, ar);

		return single;
	}

	static OpenMPElement extensionForSimd() {
		OpenMPElement simd = new OpenMPElement(OpenMPType.psimd);

		OpenMPElement safelen = new OpenMPElement(OpenMPType.psafelen);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement aligned = new OpenMPElement(OpenMPType.paligned);
		OpenMPElement nontemporal = new OpenMPElement(OpenMPType.pnontemporal);
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement simdlen = new OpenMPElement(OpenMPType.psimdlen);

		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement schedule = new OpenMPElement(OpenMPType.pschedule);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);

		OpenMPElement ar[] = { safelen, simdlen, linear, aligned, nontemporal, pprivate, lastprivate, reduction, collapse,
				order, pif, firstprivate, schedule, allocate, nowait };

		addArrayToNode(simd, ar);

		return simd;

	}

	static OpenMPElement generateForElement() {
		OpenMPElement pfor = new OpenMPElement(OpenMPType.pfor);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);

		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement schedule = new OpenMPElement(OpenMPType.pschedule);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);

		OpenMPElement ar[] = { pprivate, firstprivate, lastprivate, linear, schedule, collapse, allocate, order, reduction,
				nowait };

		addArrayToNode(pfor, ar);

		// for simd
		pfor.add(extensionForSimd());

		return pfor;

	}

	static OpenMPElement generateSimdElement() {
		OpenMPElement simd = new OpenMPElement(OpenMPType.psimd);

		OpenMPElement safelen = new OpenMPElement(OpenMPType.psafelen);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement aligned = new OpenMPElement(OpenMPType.paligned);
		OpenMPElement nontemporal = new OpenMPElement(OpenMPType.pnontemporal);
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement simdlen = new OpenMPElement(OpenMPType.psimdlen);

		OpenMPElement ar[] = { safelen, simdlen, linear, aligned, nontemporal, pprivate, lastprivate, reduction, collapse,
				order, pif };

		addArrayToNode(simd, ar);

		return simd;
	}

	static OpenMPElement generateDeclareElement() {
		OpenMPElement declare = new OpenMPElement(OpenMPType.pdeclare);

		OpenMPElement simd = new OpenMPElement(OpenMPType.psimd);
		// simd
		OpenMPElement simdlen = new OpenMPElement(OpenMPType.psimdlen);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement aligned = new OpenMPElement(OpenMPType.paligned);
		OpenMPElement uniform = new OpenMPElement(OpenMPType.puniform);
		OpenMPElement inbranch = new OpenMPElement(OpenMPType.pinbranch);
		OpenMPElement notinbranch = new OpenMPElement(OpenMPType.pnotinbranch);
		OpenMPElement arsimd[] = { simdlen, linear, aligned, uniform, inbranch, notinbranch };
		addArrayToNode(simd, arsimd);
		// end simd

		OpenMPElement variant = new OpenMPElement(OpenMPType.pvariant);
		// variant
		OpenMPElement match = new OpenMPElement(OpenMPType.pmatch);
		variant.add(match);
		// end variant

		OpenMPElement target = new OpenMPElement(OpenMPType.ptarget);
		// target
		OpenMPElement to = new OpenMPElement(OpenMPType.pto);
		OpenMPElement link = new OpenMPElement(OpenMPType.plink);
		OpenMPElement host = new OpenMPElement(OpenMPType.phost);
		OpenMPElement artarget[] = { to, link, host };
		addArrayToNode(target, artarget);
		// end target

		// reduction
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		// end reduction

		// mapper
		OpenMPElement mapper = new OpenMPElement(OpenMPType.pmapper);
		// end mapper

		declare.add(mapper);
		declare.add(reduction);
		declare.add(simd);
		declare.add(variant);
		declare.add(target);

		return declare;
	}
/*
	static OpenMPElement generateParallelElement() {
		OpenMPElement parallel = new OpenMPElement(OpenMPType.psimd);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement copyin = new OpenMPElement(OpenMPType.pcopyin);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);

		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);

		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);

		OpenMPElement arr[] = { pprivate, firstprivate, shared, copyin, proc_bind, allocate, pif, num_threads, pdefault };
		addArrayToNode(parallel, arr);

		return parallel;
	}*/

	static OpenMPElement extensionDistributeSimd() {
		OpenMPElement simd = new OpenMPElement(OpenMPType.psimd);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement dist_schedule = new OpenMPElement(OpenMPType.pdist_schedule);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement safelen = new OpenMPElement(OpenMPType.psafelen);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement aligned = new OpenMPElement(OpenMPType.paligned);
		OpenMPElement nontemporal = new OpenMPElement(OpenMPType.pnontemporal);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement simdlen = new OpenMPElement(OpenMPType.psimdlen);

		OpenMPElement arr[] = { pprivate, firstprivate, lastprivate, collapse, dist_schedule, allocate, safelen, linear,
				aligned, nontemporal, reduction, order, pif, simdlen };
		addArrayToNode(simd, arr);
		return simd;
	}

	static OpenMPElement extensionDistributeParallelFor() {
		OpenMPElement pfor = new OpenMPElement(OpenMPType.pfor);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement dist_schedule = new OpenMPElement(OpenMPType.pdist_schedule);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement copyin = new OpenMPElement(OpenMPType.pcopyin);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);

		OpenMPElement arr[] = { pprivate, firstprivate, lastprivate, collapse, dist_schedule, allocate, shared, copyin,
				proc_bind, pif, num_threads, pdefault };

		addArrayToNode(pfor, arr);

		return pfor;
	}

	static OpenMPElement extensionDistributeParallelForSimd() {
		OpenMPElement simd = new OpenMPElement(OpenMPType.psimd);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);

		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement dist_schedule = new OpenMPElement(OpenMPType.pdist_schedule);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement copyin = new OpenMPElement(OpenMPType.pcopyin);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);

		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);

		OpenMPElement safelen = new OpenMPElement(OpenMPType.psafelen);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement aligned = new OpenMPElement(OpenMPType.paligned);
		OpenMPElement nontemporal = new OpenMPElement(OpenMPType.pnontemporal);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement simdlen = new OpenMPElement(OpenMPType.psimdlen);
		OpenMPElement arr[] = { pprivate, firstprivate, lastprivate, collapse, dist_schedule, allocate, shared, copyin,
				proc_bind, pif, num_threads, pdefault, safelen, linear, aligned, nontemporal, reduction, order,
				simdlen };

		addArrayToNode(simd, arr);

		return simd;
	}

	static OpenMPElement generateDistribute() {
		OpenMPElement distribute = new OpenMPElement(OpenMPType.pdistribute);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement dist_schedule = new OpenMPElement(OpenMPType.pdist_schedule);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);

		

		OpenMPElement arr[] = { pprivate, firstprivate, lastprivate, collapse, dist_schedule, allocate };
		addArrayToNode(distribute, arr);

		OpenMPElement simd = extensionDistributeSimd();

		OpenMPElement parallel = new OpenMPElement(OpenMPType.pparallel);
		OpenMPElement pfor = extensionDistributeParallelFor();// parallel for
		parallel.add(pfor);
		pfor.add(extensionDistributeParallelForSimd());// parallel for simd

		distribute.add(simd);
		distribute.add(parallel);

		return distribute;
	}

	static OpenMPElement extensionParallelLoop() {
		OpenMPElement loop = new OpenMPElement(OpenMPType.ploop);
		
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement copy_in = new OpenMPElement(OpenMPType.pcopyin);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement bind = new OpenMPElement(OpenMPType.pbind);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);

		OpenMPElement arr[] = { pprivate, firstprivate, shared, copy_in, proc_bind, allocate, pif, num_threads, pdefault,
				bind, collapse, order, lastprivate, reduction };

		addArrayToNode(loop, arr);
		return loop;
	}
		
	static OpenMPElement extensionParallelMaster()
	{
		OpenMPElement master = new OpenMPElement(OpenMPType.pmaster);
		
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement copy_in = new OpenMPElement(OpenMPType.pcopyin);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);

		OpenMPElement arr[] = { pprivate, firstprivate, shared, copy_in, proc_bind, allocate, pif, num_threads, pdefault };
		addArrayToNode(master, arr);
		
		return master;
	}
	
	static OpenMPElement extensionParallelForSimd()
	{
		OpenMPElement simd = new OpenMPElement(OpenMPType.psimd);
		
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement copy_in = new OpenMPElement(OpenMPType.pcopyin);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement schedule = new OpenMPElement(OpenMPType.pschedule);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement safelen = new OpenMPElement(OpenMPType.psafelen);
		OpenMPElement aligned = new OpenMPElement(OpenMPType.paligned);
		OpenMPElement nontemporal = new OpenMPElement(OpenMPType.pnontemporal);
		OpenMPElement simdlen = new OpenMPElement(OpenMPType.psimdlen);

		OpenMPElement arr[] = { pprivate, firstprivate, shared, copy_in, proc_bind, allocate, pif, lastprivate, linear,
				schedule, collapse, order, reduction, num_threads, pdefault, safelen, aligned, nontemporal, simdlen};
		
		addArrayToNode(simd, arr);
		
		return simd;
	}
	static OpenMPElement extensionParallelFor() {
		OpenMPElement pfor = new OpenMPElement(OpenMPType.pfor);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement copy_in = new OpenMPElement(OpenMPType.pcopyin);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement schedule = new OpenMPElement(OpenMPType.pschedule);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);

		OpenMPElement arr[] = { pprivate, firstprivate, shared, copy_in, proc_bind, allocate, pif, lastprivate, linear,
				schedule, collapse, order, reduction, num_threads, pdefault };

		addArrayToNode(pfor, arr);
		pfor.add(extensionParallelForSimd());
		return pfor;
	}

	static OpenMPElement generateParallel() {
		OpenMPElement parallel = new OpenMPElement(OpenMPType.pparallel);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement copy_in = new OpenMPElement(OpenMPType.pcopyin);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);

		OpenMPElement arr[] = { pprivate, firstprivate, shared, copy_in, proc_bind, allocate, pif, num_threads, pdefault };
		addArrayToNode(parallel, arr);

		parallel.add(extensionParallelFor());
		parallel.add(extensionParallelLoop());
		parallel.add(new OpenMPElement(OpenMPType.psections));
		parallel.add(new OpenMPElement(OpenMPType.psection));
		parallel.add(extensionParallelMaster());

		
		return parallel;
	}

	static OpenMPElement generateLooOpenMPElement() {
		OpenMPElement loop = new OpenMPElement(OpenMPType.ploop);

		OpenMPElement bind = new OpenMPElement(OpenMPType.pbind);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);

		OpenMPElement arr[] = { bind, collapse, shared, order, pprivate, lastprivate, reduction };

		addArrayToNode(loop, arr);
		return loop;
	}

	static OpenMPElement extensionTaskloopSimd()
	{
		OpenMPElement simd = new OpenMPElement(OpenMPType.psimd);
		
		OpenMPElement safelen = new OpenMPElement(OpenMPType.psafelen);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement aligned = new OpenMPElement(OpenMPType.paligned);
		OpenMPElement nontemporal = new OpenMPElement(OpenMPType.pnontemporal);
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement simdlen = new OpenMPElement(OpenMPType.psimdlen);	
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);
		OpenMPElement grainsize = new OpenMPElement(OpenMPType.pgrainsize);
		OpenMPElement priority = new OpenMPElement(OpenMPType.ppriority);
		OpenMPElement untied = new OpenMPElement(OpenMPType.puntied);
		OpenMPElement mergeable = new OpenMPElement(OpenMPType.pmergeable);
		OpenMPElement nogroup = new OpenMPElement(OpenMPType.pnogroup);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement pfinal = new OpenMPElement(OpenMPType.pfinal);
		
		OpenMPElement ar[] = { safelen, simdlen, linear, aligned, nontemporal, pprivate, lastprivate, reduction, collapse,
				order, shared, firstprivate, in_reduction, grainsize, priority, untied, mergeable, nogroup, allocate, pif, pdefault, pfinal};
		
		addArrayToNode(simd, ar);
		
		return simd;
	}
	static OpenMPElement generateTasklooOpenMPElement() {

		OpenMPElement taskloop = new OpenMPElement(OpenMPType.ptaskloop);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);
		OpenMPElement grainsize = new OpenMPElement(OpenMPType.pgrainsize);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement priority = new OpenMPElement(OpenMPType.ppriority);
		OpenMPElement untied = new OpenMPElement(OpenMPType.puntied);
		OpenMPElement mergeable = new OpenMPElement(OpenMPType.pmergeable);
		OpenMPElement nogroup = new OpenMPElement(OpenMPType.pnogroup);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement pfinal = new OpenMPElement(OpenMPType.pfinal);

		OpenMPElement arr[] = {pprivate, lastprivate, shared, firstprivate, reduction, in_reduction, grainsize, collapse, priority, untied, mergeable, nogroup, allocate, pif ,pdefault, pfinal};
		addArrayToNode(taskloop, arr);
		taskloop.add(extensionTaskloopSimd());
		return taskloop;
	}

	
	static OpenMPElement generateMasterElement()
	{
		OpenMPElement master = new OpenMPElement(OpenMPType.pmaster);
		master.add(generateTasklooOpenMPElement());
		return master;
		
	}
	
	
	static OpenMPElement extensionTeamsDistributeParallelForSimd()
	{
		OpenMPElement simd = new OpenMPElement(OpenMPType.psimd);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement dist_schedule = new OpenMPElement(OpenMPType.pdist_schedule);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement copyin = new OpenMPElement(OpenMPType.pcopyin);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement safelen = new OpenMPElement(OpenMPType.psafelen);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement aligned = new OpenMPElement(OpenMPType.paligned);
		OpenMPElement nontemporal = new OpenMPElement(OpenMPType.pnontemporal);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement simdlen = new OpenMPElement(OpenMPType.psimdlen);
		OpenMPElement num_teams = new OpenMPElement(OpenMPType.pnum_teams);
		OpenMPElement thread_limit = new OpenMPElement(OpenMPType.pthread_limit);

		
		
		OpenMPElement arr[] = { pprivate, firstprivate, lastprivate, collapse, dist_schedule, allocate, shared, copyin,
				proc_bind, pif, num_threads, pdefault, safelen, linear, aligned, nontemporal, reduction, order,
				simdlen, num_teams, thread_limit };

		addArrayToNode(simd, arr);
		
		return simd;
	}
	
	static OpenMPElement extensionTeamsDistributeParallelFor()
	{
		OpenMPElement parallel = new OpenMPElement(OpenMPType.pparallel);
		OpenMPElement pfor = new OpenMPElement(OpenMPType.pfor);
		parallel.add(pfor);
		
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement num_teams = new OpenMPElement(OpenMPType.pnum_teams);
		OpenMPElement thread_limit = new OpenMPElement(OpenMPType.pthread_limit);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement dist_schedule = new OpenMPElement(OpenMPType.pdist_schedule);
		OpenMPElement copyin = new OpenMPElement(OpenMPType.pcopyin);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);

		OpenMPElement arr[] = { pprivate, firstprivate, lastprivate, collapse, dist_schedule, allocate, shared, copyin,
				proc_bind, pif, num_threads, pdefault, reduction, num_teams, thread_limit };

		addArrayToNode(pfor,arr);
		pfor.add(extensionTeamsDistributeParallelForSimd());
		return parallel;
	}
	
	static OpenMPElement extensionTeamsDistributeSimd()
	{
		OpenMPElement simd = new OpenMPElement(OpenMPType.psimd);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement num_teams = new OpenMPElement(OpenMPType.pnum_teams);
		OpenMPElement thread_limit = new OpenMPElement(OpenMPType.pthread_limit);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement dist_schedule = new OpenMPElement(OpenMPType.pdist_schedule);
		OpenMPElement safelen = new OpenMPElement(OpenMPType.psafelen);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement aligned = new OpenMPElement(OpenMPType.paligned);
		OpenMPElement nontemporal = new OpenMPElement(OpenMPType.pnontemporal);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement simdlen = new OpenMPElement(OpenMPType.psimdlen);

		OpenMPElement arr[] = { pprivate, firstprivate, lastprivate, collapse, dist_schedule, allocate, safelen, linear,
				aligned, nontemporal, reduction, order, pif, simdlen, num_teams, thread_limit, pdefault, shared};
		
		addArrayToNode(simd, arr);
		return simd;
		
	}
	static OpenMPElement extensionTeamsDistribute()
	{
		OpenMPElement distribute = new OpenMPElement(OpenMPType.pdistribute);
		
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement num_teams = new OpenMPElement(OpenMPType.pnum_teams);
		OpenMPElement thread_limit = new OpenMPElement(OpenMPType.pthread_limit);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement dist_schedule = new OpenMPElement(OpenMPType.pdist_schedule);
		
		OpenMPElement arr[] = { pprivate, lastprivate, shared, firstprivate, reduction, allocate, num_teams, thread_limit, pdefault, collapse, dist_schedule}; 
		
		
		addArrayToNode(distribute,arr);
		distribute.add(extensionTeamsDistributeSimd());
		return distribute;

	}
	
	static OpenMPElement extensionTeamsLoop()
	{
		OpenMPElement loop = new OpenMPElement(OpenMPType.ploop);
		
		
		OpenMPElement bind = new OpenMPElement(OpenMPType.pbind);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement num_teams = new OpenMPElement(OpenMPType.pnum_teams);
		OpenMPElement thread_limit = new OpenMPElement(OpenMPType.pthread_limit);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		
		OpenMPElement arr[] = {bind, collapse, shared, order, pprivate, lastprivate, reduction, firstprivate, allocate, num_teams, thread_limit, pdefault};
		
		addArrayToNode(loop, arr);
		return loop;
	}
	static OpenMPElement generateTeamsElement()
	{
		OpenMPElement teams = new OpenMPElement(OpenMPType.pteams);
		


		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement num_teams = new OpenMPElement(OpenMPType.pnum_teams);
		OpenMPElement thread_limit = new OpenMPElement(OpenMPType.pthread_limit);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);

		OpenMPElement arr[] = { pprivate, lastprivate, shared, firstprivate, reduction, allocate, num_teams, thread_limit, pdefault}; 

		addArrayToNode(teams, arr);
		
		teams.add(extensionTeamsDistribute());
		teams.add(extensionTeamsLoop());
		return teams;
	}
		
	static OpenMPElement extensionTargetUpdate()
	{
		OpenMPElement update = new OpenMPElement(OpenMPType.pupdate);
		
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		OpenMPElement to = new OpenMPElement(OpenMPType.pto);
		OpenMPElement from = new OpenMPElement(OpenMPType.pfrom);
		
		
		OpenMPElement arr[] = {nowait, depend, pif, device, to, from};
		
		addArrayToNode(update, arr);
 		return update;
		
	}
	
	
	
	static OpenMPElement extensionTargetData()
	{
		OpenMPElement data = new OpenMPElement(OpenMPType.pdata);
		
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement use_device_ptr = new OpenMPElement(OpenMPType.puse_device_ptr);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		
		OpenMPElement arr[] = {map, use_device_ptr, pif, device};
		addArrayToNode(data, arr);
		
 		return data;
	}
	
	static OpenMPElement extensionTargetExitData()
	{
		OpenMPElement exit = new OpenMPElement(OpenMPType.penter);
		OpenMPElement data = new OpenMPElement(OpenMPType.pdata);
		
		exit.add(data);
		
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);		
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		
		OpenMPElement arr[] = {map, depend,  pif, device};
		addArrayToNode(data, arr);
		
 		return exit;
	}
	
	
	static OpenMPElement extensionTargetEnterData()
	{
		OpenMPElement enter = new OpenMPElement(OpenMPType.penter);
		OpenMPElement data = new OpenMPElement(OpenMPType.pdata);
		
		enter.add(data);
		
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		
		OpenMPElement arr[] = {map, depend, nowait,  pif, device};
		addArrayToNode(data, arr);
		
 		return enter;
	}
	
	static OpenMPElement extensionTargetParallelForSimd()
	{
		OpenMPElement simd = new OpenMPElement(OpenMPType.psimd);
		
		OpenMPElement target = new OpenMPElement(OpenMPType.ptarget);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement is_device_ptr = new OpenMPElement(OpenMPType.pis_device_ptr);
		OpenMPElement defaultmap = new OpenMPElement(OpenMPType.pdefaultmap);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement uses_allocators = new OpenMPElement(OpenMPType.puses_allocators);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement schedule = new OpenMPElement(OpenMPType.pschedule);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement safelen = new OpenMPElement(OpenMPType.psafelen);
		OpenMPElement aligned = new OpenMPElement(OpenMPType.paligned);
		OpenMPElement nontemporal = new OpenMPElement(OpenMPType.pnontemporal);
		OpenMPElement simdlen = new OpenMPElement(OpenMPType.psimdlen);
		OpenMPElement arr[] = { pprivate, firstprivate, shared, proc_bind, allocate, pif, lastprivate, linear,
				schedule, collapse, order, reduction, num_threads, pdefault, target, in_reduction, map, is_device_ptr, 
				defaultmap, nowait, depend, uses_allocators, device, safelen, aligned, nontemporal, simdlen};
		
		addArrayToNode(simd, arr);
		
		return simd;
	}

	static OpenMPElement extensionTargetParallelFor()
	{
		OpenMPElement pfor = new OpenMPElement(OpenMPType.pfor);
	
		OpenMPElement target = new OpenMPElement(OpenMPType.ptarget);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement is_device_ptr = new OpenMPElement(OpenMPType.pis_device_ptr);
		OpenMPElement defaultmap = new OpenMPElement(OpenMPType.pdefaultmap);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement uses_allocators = new OpenMPElement(OpenMPType.puses_allocators);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement schedule = new OpenMPElement(OpenMPType.pschedule);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);

		OpenMPElement arr[] = { pprivate, firstprivate, shared, proc_bind, allocate, pif, lastprivate, linear,
				schedule, collapse, order, reduction, num_threads, pdefault, target, in_reduction, map, is_device_ptr, 
				defaultmap, nowait, depend, uses_allocators, device};
		
		addArrayToNode(pfor, arr);
		
		pfor.add(extensionTargetParallelForSimd());

		return pfor;
	}

	
	static OpenMPElement extensionTargetParallelLoop()
	{
		
		OpenMPElement loop = new OpenMPElement(OpenMPType.ploop);
		
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement num_teams = new OpenMPElement(OpenMPType.pnum_teams);
		OpenMPElement thread_limit = new OpenMPElement(OpenMPType.pthread_limit);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);	
		OpenMPElement bind = new OpenMPElement(OpenMPType.pbind);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		
		OpenMPElement arr[] = {pprivate, firstprivate, shared, reduction,
				num_teams, thread_limit, pdefault, proc_bind, allocate, pif, num_threads, bind, collapse, order, lastprivate};
		
		addArrayToNode(loop,arr);
		
		return loop;
	}
	static OpenMPElement extensionTargetParallel()
	{
		OpenMPElement parallel = new OpenMPElement(OpenMPType.pparallel);
				
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement is_device_ptr = new OpenMPElement(OpenMPType.pis_device_ptr);
		OpenMPElement defaultmap = new OpenMPElement(OpenMPType.pdefaultmap);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement uses_allocators = new OpenMPElement(OpenMPType.puses_allocators);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		
		
		OpenMPElement arr[] = {pif, pprivate, firstprivate, shared, in_reduction, reduction, proc_bind, map, is_device_ptr, defaultmap, nowait, depend, num_threads, pdefault, allocate
				, uses_allocators, device};
		addArrayToNode(parallel, arr);
		
		parallel.add(extensionTargetParallelFor());
		parallel.add(extensionTargetParallelLoop());

		return parallel;
	}
	
	
	static OpenMPElement extensionTargetSimd()
	{
		OpenMPElement simd = new OpenMPElement(OpenMPType.psimd);
		
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement is_device_ptr = new OpenMPElement(OpenMPType.pis_device_ptr);
		OpenMPElement defaultmap = new OpenMPElement(OpenMPType.pdefaultmap);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement uses_allocators = new OpenMPElement(OpenMPType.puses_allocators);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		OpenMPElement safelen = new OpenMPElement(OpenMPType.psafelen);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement aligned = new OpenMPElement(OpenMPType.paligned);
		OpenMPElement nontemporal = new OpenMPElement(OpenMPType.pnontemporal);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement simdlen = new OpenMPElement(OpenMPType.psimdlen);

		OpenMPElement arr[] = {pif, pprivate, in_reduction, map, is_device_ptr, defaultmap, nowait, depend, allocate, uses_allocators, device,
				safelen, linear, aligned, nontemporal, lastprivate, reduction, collapse, order, simdlen};
		
		addArrayToNode(simd, arr);
		
		return simd;
	}
	
	static OpenMPElement extensionTargetTeamsDistributeSimd()
	{

		OpenMPElement simd = new OpenMPElement(OpenMPType.psimd);

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement num_teams = new OpenMPElement(OpenMPType.pnum_teams);
		OpenMPElement thread_limit = new OpenMPElement(OpenMPType.pthread_limit);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement dist_schedule = new OpenMPElement(OpenMPType.pdist_schedule);
		OpenMPElement safelen = new OpenMPElement(OpenMPType.psafelen);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement aligned = new OpenMPElement(OpenMPType.paligned);
		OpenMPElement nontemporal = new OpenMPElement(OpenMPType.pnontemporal);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement simdlen = new OpenMPElement(OpenMPType.psimdlen);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement is_device_ptr = new OpenMPElement(OpenMPType.pis_device_ptr);
		OpenMPElement defaultmap = new OpenMPElement(OpenMPType.pdefaultmap);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement uses_allocators = new OpenMPElement(OpenMPType.puses_allocators);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		
		
		OpenMPElement arr[] = { pprivate, firstprivate, lastprivate, collapse, dist_schedule, allocate, safelen, linear,
				aligned, nontemporal, reduction, order, pif, simdlen, num_teams, thread_limit, pdefault, shared,
				in_reduction, map, is_device_ptr, defaultmap, nowait, depend, uses_allocators, device};
		
		addArrayToNode(simd, arr);
		return simd;
	}
	
	
	static OpenMPElement extensionTargetTeamsDistributeParallelForSimd()
	{
		OpenMPElement simd = new OpenMPElement(OpenMPType.psimd);
		
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement num_teams = new OpenMPElement(OpenMPType.pnum_teams);
		OpenMPElement thread_limit = new OpenMPElement(OpenMPType.pthread_limit);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement dist_schedule = new OpenMPElement(OpenMPType.pdist_schedule);
		OpenMPElement copyin = new OpenMPElement(OpenMPType.pcopyin);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement is_device_ptr = new OpenMPElement(OpenMPType.pis_device_ptr);
		OpenMPElement defaultmap = new OpenMPElement(OpenMPType.pdefaultmap);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement uses_allocators = new OpenMPElement(OpenMPType.puses_allocators);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		OpenMPElement safelen = new OpenMPElement(OpenMPType.psafelen);
		OpenMPElement linear = new OpenMPElement(OpenMPType.plinear);
		OpenMPElement aligned = new OpenMPElement(OpenMPType.paligned);
		OpenMPElement nontemporal = new OpenMPElement(OpenMPType.pnontemporal);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement simdlen = new OpenMPElement(OpenMPType.psimdlen);
		
		OpenMPElement arr[] = { pprivate, firstprivate, lastprivate, collapse, dist_schedule, allocate, shared, copyin,
				proc_bind, pif, num_threads, pdefault, reduction, num_teams, thread_limit, in_reduction, map, is_device_ptr,
				defaultmap, nowait, depend, uses_allocators, device, safelen, linear, aligned, nontemporal, order, simdlen};
		
		addArrayToNode(simd, arr);
		
		return simd;
	}
	static OpenMPElement extensionTargetTeamsDistributeParallelFor()
	{
		OpenMPElement parallel = new OpenMPElement(OpenMPType.pparallel);
		OpenMPElement pfor = new OpenMPElement(OpenMPType.pfor);
		parallel.add(pfor);
		
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement num_teams = new OpenMPElement(OpenMPType.pnum_teams);
		OpenMPElement thread_limit = new OpenMPElement(OpenMPType.pthread_limit);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement dist_schedule = new OpenMPElement(OpenMPType.pdist_schedule);
		OpenMPElement copyin = new OpenMPElement(OpenMPType.pcopyin);
		OpenMPElement proc_bind = new OpenMPElement(OpenMPType.pproc_bind);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement num_threads = new OpenMPElement(OpenMPType.pnum_threads);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement is_device_ptr = new OpenMPElement(OpenMPType.pis_device_ptr);
		OpenMPElement defaultmap = new OpenMPElement(OpenMPType.pdefaultmap);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement uses_allocators = new OpenMPElement(OpenMPType.puses_allocators);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		OpenMPElement arr[] = { pprivate, firstprivate, lastprivate, collapse, dist_schedule, allocate, shared, copyin,
				proc_bind, pif, num_threads, pdefault, reduction, num_teams, thread_limit, in_reduction, map, is_device_ptr,
				defaultmap, nowait, depend, uses_allocators, device};
		
		addArrayToNode(pfor, arr);
		
		pfor.add(extensionTargetTeamsDistributeParallelForSimd());
		
		return parallel;
	}
	
	static OpenMPElement extensionTargetTeamsDistribute()
	{
		OpenMPElement distribute = new OpenMPElement(OpenMPType.pdistribute);

		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);	
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement is_device_ptr = new OpenMPElement(OpenMPType.pis_device_ptr);
		OpenMPElement defaultmap = new OpenMPElement(OpenMPType.pdefaultmap);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement uses_allocators = new OpenMPElement(OpenMPType.puses_allocators);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement num_teams = new OpenMPElement(OpenMPType.pnum_teams);
		OpenMPElement thread_limit = new OpenMPElement(OpenMPType.pthread_limit);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement dist_schedule = new OpenMPElement(OpenMPType.pdist_schedule);

		
		OpenMPElement arr[] = { pprivate, firstprivate, lastprivate, collapse, dist_schedule, allocate, pif, in_reduction,
				map, is_device_ptr, defaultmap, nowait, depend, uses_allocators, device, shared, reduction, num_teams,
				thread_limit, pdefault};

		addArrayToNode(distribute, arr);
		
		
		distribute.add(extensionTargetTeamsDistributeSimd());
		distribute.add(extensionTargetTeamsDistributeParallelFor());
		return distribute;
	}
	
	static OpenMPElement extensionTargetTeamsLoop()
	{
		OpenMPElement loop = new OpenMPElement(OpenMPType.ploop);
		
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement is_device_ptr = new OpenMPElement(OpenMPType.pis_device_ptr);
		OpenMPElement defaultmap = new OpenMPElement(OpenMPType.pdefaultmap);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement uses_allocators = new OpenMPElement(OpenMPType.puses_allocators);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		OpenMPElement bind = new OpenMPElement(OpenMPType.pbind);
		OpenMPElement collapse = new OpenMPElement(OpenMPType.pcollapse);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement order = new OpenMPElement(OpenMPType.porder);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement num_teams = new OpenMPElement(OpenMPType.pnum_teams);
		OpenMPElement thread_limit = new OpenMPElement(OpenMPType.pthread_limit);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		
		OpenMPElement arr[] = {bind, collapse, shared, order, pprivate, lastprivate, reduction, firstprivate, allocate, num_teams, thread_limit, pdefault,
				pif, in_reduction, map, is_device_ptr, defaultmap, nowait, depend, uses_allocators, device};
		
		addArrayToNode(loop, arr);
		
		return loop;
		
	}
	static OpenMPElement extensionTargetTeams()
	{
		OpenMPElement teams = new OpenMPElement(OpenMPType.pteams);
		

		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement lastprivate = new OpenMPElement(OpenMPType.plastprivate, true);
		OpenMPElement shared = new OpenMPElement(OpenMPType.pshared, true);
		OpenMPElement firstprivate = new OpenMPElement(OpenMPType.pfirstprivate, true);
		OpenMPElement reduction = new OpenMPElement(OpenMPType.preduction);		
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement num_teams = new OpenMPElement(OpenMPType.pnum_teams);
		OpenMPElement thread_limit = new OpenMPElement(OpenMPType.pthread_limit);
		OpenMPElement pdefault = new OpenMPElement(OpenMPType.pdefault);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement is_device_ptr = new OpenMPElement(OpenMPType.pis_device_ptr);
		OpenMPElement defaultmap = new OpenMPElement(OpenMPType.pdefaultmap);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement uses_allocators = new OpenMPElement(OpenMPType.puses_allocators);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		
		OpenMPElement arr[] = {pif, pprivate, in_reduction, map, is_device_ptr, defaultmap, nowait, depend, allocate, uses_allocators, device,
				lastprivate, shared, firstprivate, reduction, allocate, num_teams, thread_limit, pdefault};
		
		
		addArrayToNode(teams, arr);
		
		teams.add(extensionTargetTeamsDistribute());
		teams.add(extensionTargetTeamsLoop());
		return teams;
	}
	static OpenMPElement generateTargetElement()
	{
		OpenMPElement target = new OpenMPElement(OpenMPType.ptarget);
		
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		OpenMPElement pprivate = new OpenMPElement(OpenMPType.pprivate, true);
		OpenMPElement in_reduction = new OpenMPElement(OpenMPType.pin_reduction);
		OpenMPElement map = new OpenMPElement(OpenMPType.pmap);
		OpenMPElement is_device_ptr = new OpenMPElement(OpenMPType.pis_device_ptr);
		OpenMPElement defaultmap = new OpenMPElement(OpenMPType.pdefaultmap);
		OpenMPElement nowait = new OpenMPElement(OpenMPType.pnowait);
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement allocate = new OpenMPElement(OpenMPType.pallocate);
		OpenMPElement uses_allocators = new OpenMPElement(OpenMPType.puses_allocators);
		OpenMPElement device = new OpenMPElement(OpenMPType.pdevice);
		
		OpenMPElement arr[] = {pif, pprivate, in_reduction, map, is_device_ptr, defaultmap, nowait, depend, allocate, uses_allocators, device};
		
		
		addArrayToNode(target, arr);
		
		
		//target update
		target.add(extensionTargetUpdate());
		//target data
		target.add(extensionTargetData());
		//target enter data
		target.add(extensionTargetEnterData());
		//target exit data
		target.add(extensionTargetExitData());
		//target parallel
		//target parallel for
		//target parallel for simd
		//target parallel loop
		target.add(extensionTargetParallel());
		//target simd
		target.add(extensionTargetSimd());
		//target teams
		target.add(extensionTargetTeams());
		return target;
	}
		
	static OpenMPElement generateScanElement()
	{
		OpenMPElement scan = new OpenMPElement(OpenMPType.pscan);
		
		OpenMPElement inclusive = new OpenMPElement(OpenMPType.pinclusive);
		OpenMPElement exclusive = new OpenMPElement(OpenMPType.pexclusive);
		
		OpenMPElement arr[] = {inclusive, exclusive};
		
		addArrayToNode(scan, arr);
		
		return scan;
	}
	
	
	static OpenMPElement generateRequiresElement()
	{
		OpenMPElement requires = new OpenMPElement(OpenMPType.prequires);
		
		OpenMPElement reverse_offload = new OpenMPElement(OpenMPType.preverse_offload);
		OpenMPElement unified_address = new OpenMPElement(OpenMPType.punified_address);
		OpenMPElement unified_shared_memory = new OpenMPElement(OpenMPType.punified_shared_memory);
		OpenMPElement atomic_default_mem_order = new OpenMPElement(OpenMPType.patomic_default_mem_order);
		OpenMPElement dynamic_allocators = new OpenMPElement(OpenMPType.pdynamic_allocators);
		
		
		OpenMPElement arr[] = {reverse_offload, unified_address, unified_shared_memory, atomic_default_mem_order, dynamic_allocators};
		
		addArrayToNode(requires, arr);

		return requires;
		
	}
	
	
	static OpenMPElement generateDepobjElement()
	{
		OpenMPElement depobj = new OpenMPElement(OpenMPType.pdepobj);
		
		OpenMPElement depend = new OpenMPElement(OpenMPType.pdepend);
		OpenMPElement destroy = new OpenMPElement(OpenMPType.pdestroy);
		OpenMPElement update = new OpenMPElement(OpenMPType.pupdate);
		
		OpenMPElement arr[] = {depend, destroy, update};
		
		addArrayToNode(depobj, arr);
		return depobj;
		
	}
	
	static OpenMPElement generateCancelElement()
	{
		OpenMPElement cancel = new OpenMPElement(OpenMPType.pcancel);
		
		OpenMPElement parallel = new OpenMPElement(OpenMPType.pparallel);
		OpenMPElement sections = new OpenMPElement(OpenMPType.psections);
		OpenMPElement taskgroup = new OpenMPElement(OpenMPType.ptaskgroup);
		OpenMPElement pfor = new OpenMPElement(OpenMPType.pfor);
		OpenMPElement pif = new OpenMPElement(OpenMPType.pif);
		
		
		OpenMPElement arr[] = {parallel, sections, taskgroup, pfor, pif};
		
		addArrayToNode(cancel, arr);
		
		return cancel;
	}
	
	static OpenMPElement generateCancellationElement()
	{
		OpenMPElement cancellation = new OpenMPElement(OpenMPType.pcancellation);
		OpenMPElement point = new OpenMPElement(OpenMPType.ppoint);
		
		cancellation.add(point);
		
		OpenMPElement parallel = new OpenMPElement(OpenMPType.pparallel);
		OpenMPElement sections = new OpenMPElement(OpenMPType.psections);
		OpenMPElement taskgroup = new OpenMPElement(OpenMPType.ptaskgroup);
		OpenMPElement pfor = new OpenMPElement(OpenMPType.pfor);
		
		OpenMPElement arr[] = {parallel, sections, taskgroup, pfor};
		
		addArrayToNode(point, arr);
		return cancellation;
		
	}
	
	
	static OpenMPElement generateAtomicElement()
	{
		OpenMPElement atomic = new OpenMPElement(OpenMPType.patomic);
		OpenMPElement hint = new OpenMPElement(OpenMPType.phint);

		OpenMPElement read = new OpenMPElement(OpenMPType.pread);
		OpenMPElement write = new OpenMPElement(OpenMPType.pwrite);
		OpenMPElement update = new OpenMPElement(OpenMPType.pupdate);
		OpenMPElement capture = new OpenMPElement(OpenMPType.pcapture);
		
		atomic.add(read);
		atomic.add(write);
		atomic.add(update);
		atomic.add(capture);
		
		OpenMPElement seq_cst  = new OpenMPElement(OpenMPType.pseq_cst);
		OpenMPElement acq_rel  = new OpenMPElement(OpenMPType.pacq_rel);
		OpenMPElement release  = new OpenMPElement(OpenMPType.prelease);
		OpenMPElement acquire  = new OpenMPElement(OpenMPType.pacquire);
		OpenMPElement relaxed  = new OpenMPElement(OpenMPType.prelaxed);
		
		seq_cst.add(hint);
		acq_rel.add(hint);
		release.add(hint);
		acquire.add(hint);
		relaxed.add(hint);
		
		read.add(seq_cst);
		read.add(acq_rel);
		read.add(release);
		read.add(acquire);
		read.add(relaxed);
		
		write.add(seq_cst);
		write.add(acq_rel);
		write.add(release);
		write.add(acquire);
		write.add(relaxed);
		
		update.add(seq_cst);
		update.add(acq_rel);
		update.add(release);
		update.add(acquire);
		update.add(relaxed);
		
		capture.add(seq_cst);
		capture.add(acq_rel);
		capture.add(release);
		capture.add(acquire);
		capture.add(relaxed);
		
		return atomic;
	}
	
	static OpenMPElement generateOrderedElement()
	{
		OpenMPElement ordered = new OpenMPElement(OpenMPType.pordered);
		
		ordered.add(new OpenMPElement(OpenMPType.psimd));
		ordered.add(new OpenMPElement(OpenMPType.pthreads));
		
		ordered.add(new OpenMPElement(OpenMPType.pdepend));
		
		return ordered;
		
	}

	
	static {
		// omp
		OpenMPElement omp = new OpenMPElement(OpenMPType.pomp);
		pragma.add(omp);


		// #pragma omp task
		omp.add(generateTaskElement());
		// #pragma omp taskwait
		omp.add(generateTaskwaitElement());
		// #pragma omp release
		omp.add(generateReleaseElement());
		// #pragma omp begin metadirective
		omp.add(generateBeginMetadirectiveElement());
		// #pragma omp end metadirective
		omp.add(generateEndElement());
		// #pragma omp sections
		omp.add(generateSectionsElement());
		omp.add(new OpenMPElement(OpenMPType.psection));
		// #pragma omp simd
		omp.add(generateSimdElement());
		// #pragma omp single
		omp.add(generateSingleElement());
		// #pragma omp for
		// #pragma omp for simd
		omp.add(generateForElement());
		// #pragma omp declare simd
		// #pragma omp declare variant
		// #pragma omp declare target
		// #pragma omp declare reduction
		// #pragma omp declare mapper
		omp.add(generateDeclareElement());
		// #pragma omp distribute
		// #pragma omp distribute parallel for
		// #pragma omp distribute parallel for simd
		omp.add(generateDistribute());
		// #pragma omp loop
		omp.add(generateLooOpenMPElement());
		// #pragma omp parallel
		// #pragma omp parallel sections
		// #pragma omp parallel section
		// #pragma omp parallel for
		// #pragma omp parallel for simd
		// #pragma omp parallel loop	
		// #pragma omp parallel master
		omp.add(generateParallel());
		// #pragma omp taskloop
		// #pragma omp taskloop simd
		omp.add(generateTasklooOpenMPElement());
		// #pragma omp master
		// #pragma omp master taskloop
		// #pragma omp master taskloop simd
		omp.add(generateMasterElement());
		// #pragma omp teams
		// #pragma omp teams loop
		// #pragma omp teams distribute
		// #pragma omp teams distribute simd
		// #pragma omp teams distribute parallel for
		// #pragma omp teams distribute parallel for simd
		omp.add(generateTeamsElement());
		// #pragma omp target
		// #pragma omp target update
		// #pragma omp target data
		// #pragma omp target enter data
		// #pragma omp target exit data
		// #pragma omp target parallel
		// #pragma omp target parallel for
		// #pragma omp target parallel for simd
		// #pragma omp target parallel loop
		// #pragma omp target simd
		// #pragma omp target teams
		// #pragma omp target teams distribute
		// #pragma omp target teams distribute simd
		// #pragma omp target teams distribute parallel for
		// #pragma omp target teams distribute parallel for simd
		// #pragma omp target teams loop
		omp.add(generateTargetElement());
		// #pragma omp scan
		omp.add(generateScanElement());
		// #pragma omp requires 
		omp.add(generateRequiresElement());
		// #pragma omp depobj
		omp.add(generateDepobjElement());
		// #pragma omp cancel
		omp.add(generateCancelElement());
		// #pragma omp cancellation point
		omp.add(generateCancellationElement());
		// #pragma omp threadprivate
		omp.add(new OpenMPElement(OpenMPType.pthreadprivate));
		// #pragma omp flush
		omp.add(new OpenMPElement(OpenMPType.pflush));
		// #pragma omp atomic
		omp.add(generateAtomicElement());
		// #pragma omp ordered
		omp.add(generateOrderedElement());
		// #pragma omp barrier
		omp.add(new OpenMPElement(OpenMPType.pbarrier));
		// #pragma omp critical
		omp.add(new OpenMPElement(OpenMPType.pcritical));
		// #pragma omp taskyield
		omp.add(new OpenMPElement(OpenMPType.ptaskyield));
		// #pragma omp allocate
		omp.add(new OpenMPElement(OpenMPType.pallocate));
	}

	public enum OpenMPType {
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
		ptaskloop("taskloop"), pcopyin("copyin"), preduction("reduction"), pproc_bind("proc_bind"),
		pnum_threads("num_threads"), pdefault("default"), pallocate("allocate"), ploop("loop"),

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
		none("");

		private String token;
		private boolean needExpr;
		public int offset;

		OpenMPType(String text) {
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
			switch(this)
			{
			case pprivate:
			case pshared:
			case pfirstprivate:
			case plastprivate:
			case pdepend:
				return true;
			default:
				return false;
			}
			
		}
		
	}

	
	
	void recursivelyRunJson(OpenMPElement node, JSONObject currentNode)
	{
			JSONArray defaults	= new JSONArray();
				
			for(OpenMPElement a : node.getNextStates())
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
			FileWriter a = new FileWriter("OpenMP/tree.json");
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
		for(OpenMPType t : OpenMPType.values())
		{
			JSONObject type = new JSONObject();
			base.put(t.getDisplayText(), type);
			type.put("repeatable", t.isRepeatable());
			type.put("expression", t.needExpr);
		}
		
		try {
			FileWriter a = new FileWriter("OpenMP/tokens.json");
			a.write(base.toString(4));
			a.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}