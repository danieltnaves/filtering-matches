class Height  {
    getQueryString = (filters) => {
        return 'height' in filters && filters['height'] > 135 ? '&height=' + filters['height'] : '';
    }
}

export default (Height);
