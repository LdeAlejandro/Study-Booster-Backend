UPDATE preferences SET last_notified_at = extract(epoch from now()) * 1000 WHERE last_notified_at IS NULL;
