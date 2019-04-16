
class FavouriteFilter {
    
    getQueryString(filters) {
        return 'favourite' in filters && filters['favourite'] ? '&favourite=true' : ''
    }

}

export default (FavouriteFilter);