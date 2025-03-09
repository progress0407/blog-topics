package io.philz.jpa_multi_entity.app.global;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class PageResponse<T> {

	private int totalPages; // 총 페이지 수
	private long totalElements; // 총 갯수
	private List<T> content;

	public PageResponse(List<T> content, Long totalElements, int pageSize) {

		this.totalElements = getTotalElementsVal(totalElements);
		this.totalPages = getTotalPages(this.totalElements, pageSize);
		this.content = content;
	}

	private static int getTotalPages(long totalElements, int pageSize) {

		if (totalElements == 0L) {
			return 0;
		}

		return 1 + ((int) totalElements - 1) / pageSize;
	}

	private static long getTotalElementsVal(Long totalElements) {
		return totalElements != null ? totalElements : 0L;
	}
}
