package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import java.util.Iterator;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 */
public class BeanMapper {
	private static DozerBeanMapper dozer = new DozerBeanMapper();

	public BeanMapper() {
    }

    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        ArrayList destinationList = Lists.newArrayList();
        Iterator i$ = sourceList.iterator();

        while(i$.hasNext()) {
            Object sourceObject = i$.next();
            Object destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }

        return destinationList;
    }

    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }
}
