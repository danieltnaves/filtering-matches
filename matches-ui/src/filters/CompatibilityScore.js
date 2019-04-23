class CompatibilityScore  {
    getQueryString = (filters) => {
        return 'compatibilityScore' in filters && filters['compatibilityScore'] > 1 ? '&compatibility_score=' + (filters['compatibilityScore'] / 100) : '';
    }
}

export default (CompatibilityScore);