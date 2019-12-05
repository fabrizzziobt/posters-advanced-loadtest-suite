package posters.pages.components.general;

import com.xceptance.loadtest.api.hpu.LookUpResult;
import com.xceptance.loadtest.api.pages.Page;
import com.xceptance.loadtest.api.pages.components.Component;
import com.xceptance.loadtest.api.util.Context;

public class Navigation implements Component
{
    public final static Navigation instance = new Navigation();

    /**
     * Lookup the navigation.
     */
    @Override
    public LookUpResult locate()
    {
        return Header.instance.locate().byCss("#categoryMenu");
    }

    /**
     * Indicates if this component exists
     *
     * @return
     */
    @Override
    public boolean exists()
    {
        return locate().exists();
    }

    public LookUpResult getTopCategories()
    {
        return filterinvalidLinks(locate().byCss(".header-menu-item a.topCategoryMenuItem"));
    }

    public LookUpResult getCategories()
    {
        return filterinvalidLinks(locate().byCss(".header-menu-item a:not(.topCategoryMenuItem)"));
    }

    private LookUpResult filterinvalidLinks(final LookUpResult links)
    {
        return links.filter(Page.VALIDLINKS)
                        .discard(Context.configuration().filterCategoryUrls.unweightedList(),
                        e -> e.getAttribute("href"));
    }
}