/*
 * COPYRIGHT...
 */
package design.patterns.facade.dto;

import java.util.List;

import lombok.Data;

/**
 * Class to PostmanResponse.
 *
 * @author Eduardo
 */
@Data
public class PostmanResponse {

	private List<PostmanCollection> collections;

}
