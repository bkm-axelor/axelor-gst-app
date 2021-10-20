package com.axelor.gst.service;

import com.axelor.gst.db.Sequence;
import com.axelor.gst.db.repo.SequenceRepository;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public class SequenceServiceImpl implements SequenceService {
	@Inject
	SequenceRepository seqRepo;

	// saving seq for first should have number 1
	@Override
	public String generateNextNbr(Sequence seq) {
		// add logic to concate prefix suffix here and return

		String prefix = seq.getPrefix();
		String suffix = seq.getSuffix();
		Integer padding = seq.getPadding();
		String nextnum;

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < padding; i++) {

			sb.append("0");
		}
		if (prefix != null & padding != null & suffix != null) {
			nextnum = prefix.concat(sb.toString()).concat(suffix);
		} else {
			nextnum = prefix.concat(sb.toString());
		}
		return nextnum;
	}

	@Override
	public String getNextNumber(String modelName) {
		Sequence sequence = seqRepo.all().filter("self.model.name = ?", modelName).fetchOne();
//		String nextNumber = sequence.getNextNumber();
//		 this will increment ans save in db
		String autoIncrementSeq = autoIncrementSeq(sequence);
		sequence.setNextNumber(autoIncrementSeq);
		seqRepo.save(sequence);
		return autoIncrementSeq;
	}

	@Override
	@Transactional
	public String autoIncrementSeq(Sequence sequence) {

		String finalconcat = "";

		if (sequence != null) {

			String prefix = sequence.getPrefix();
			String suffix = sequence.getSuffix();
			String nextNumber = sequence.getNextNumber();
			Integer padding = sequence.getPadding();

			String numberOnly = nextNumber.replaceAll("[^0-9]", "");
			int parseInt3 = Integer.parseInt(numberOnly) + 1;
			String string2 = Integer.toString(parseInt3);
			int length2 = string2.length();

			StringBuilder sb = new StringBuilder();
			for (int i = padding; i > length2; i--) {
				sb.append("0");
			}
			String str = sb.append(string2).toString();

			if (prefix != null & padding != null & suffix != null) {
				finalconcat = prefix.concat(str).concat(suffix);
			} else {
				finalconcat = prefix.concat(str);
			}

		}
		// if seq null throw error from here else return seq.getNextNbr and increment
		// NextUmber

		return finalconcat;
	}

}
