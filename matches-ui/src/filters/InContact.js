class InContact  {
    getQueryString = (filters) => {
        return 'inContact' in filters && filters['inContact'] ? '&in_contact=true' : '';
    }
}

export default (InContact);