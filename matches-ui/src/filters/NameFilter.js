class NameFilter {

    getQueryString(filters) {
        return 'name' in filters && filters['name'] !== '' ? '&name=' + filters['name'] : '';
    }

}
  
  export default (NameFilter);