class Age  {
    getQueryString = (filters) => {
        return 'age' in filters && filters['age'] !== '' && filters['age'] > 18 ? '&age=' + filters['age'] : '';
    }
}

export default (Age);
