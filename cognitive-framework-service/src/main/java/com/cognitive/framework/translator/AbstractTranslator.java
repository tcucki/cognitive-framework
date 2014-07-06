package com.cognitive.framework.translator;

import com.cognitive.framework.model.EntityModel;
import com.cognitive.framework.vo.ValueObject;

public abstract class AbstractTranslator <I, V extends ValueObject<I>, E extends EntityModel> {

	public abstract V translateEntityToVO(E entityModel);
	
	public abstract E translateVoToEntity(V valueObject);
}
