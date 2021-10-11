package com.axelor.gst.service;

import com.axelor.gst.db.Sequence;
import com.axelor.gst.db.repo.SequenceRepository;
import com.google.inject.Inject;

public class SequenceServiceImpl implements SequenceService {
	@Inject
	SequenceRepository seqRepo;

	@Override
	public String generateNextNbr(Sequence seq) {
		// add logic to concate prefix suffix here and return

		String prefix = seq.getPrefix();
		String suffix = seq.getSuffix();
		Integer padding = seq.getPadding();
		String nextnum;

		System.out.println(" " + prefix + " " + suffix + " " + padding);

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
	public String getNextNbrAndIncrement(String modelName) {
		Sequence seq = seqRepo.all().filter("self.model.name = ?", modelName).fetchOne();
		/*
		 * if(seq != null) { seq.get }
		 */

//		System.out.println(modelName);

//		System.out.println("hio");
//		System.out.println(seq);
		if (seq != null) {

			String prefix = seq.getPrefix();
			String suffix = seq.getSuffix();
			String nextNumber = seq.getNextNumber();
			Integer padding = seq.getPadding();

			String[] split = nextNumber.split(prefix);

			String string = split[1];
			int parseInt;
			int length;
			String finalconcat;

			if (suffix != null) {
				String[] split2 = string.split(suffix);

				parseInt = Integer.parseInt(split2[0]);

				int parseInt2 = Integer.parseInt(split2[0].replaceAll("[^0-9]", "") + 1);

				String s = Integer.toString(parseInt2);

				length = s.length();

			} else {
				parseInt = Integer.parseInt(split[1]);

				int parseInt2 = Integer.parseInt(split[1].replaceAll("[^0-9]", "") + 1);

				String s = Integer.toString(parseInt2);

				length = s.length();
			}

//		System.out.println(parseInt);

			StringBuilder sb = new StringBuilder();
			for (int i = padding; i > length; i--) {
				sb.append("0");
			}
			String str = sb.append("" + length).toString();

//		System.out.println("next number is "+sb);

			if (prefix != null & padding != null & suffix != null) {
				finalconcat = prefix.concat(str).concat(suffix);
			} else {
				finalconcat = prefix.concat(str);
			}

			return finalconcat;
		} else {
			return null;
		}

		// if seq null throw error from here else return seq.getNextNbr and increment
		// NextUmber

	}

}
