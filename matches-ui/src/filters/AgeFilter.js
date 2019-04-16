
class AgeFilter {
    
    getQueryString(filters) {
        return 'age' in filters && filters['age'] > 18 ? '&age=' + filters['age'] : ''
    }

}

export default (AgeFilter);