// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CustomFilters.proto

package filters.generated;

public final class FilterProtos {
    private FilterProtos() {}
    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
    }
    public interface CustomFilterOrBuilder extends
            // @@protoc_insertion_point(interface_extends:CustomFilter)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>required bytes value = 1;</code>
         */
        boolean hasValue();
        /**
         * <code>required bytes value = 1;</code>
         */
        com.google.protobuf.ByteString getValue();
    }
    /**
     * Protobuf type {@code CustomFilter}
     */
    public static final class CustomFilter extends
            com.google.protobuf.GeneratedMessage implements
            // @@protoc_insertion_point(message_implements:CustomFilter)
            CustomFilterOrBuilder {
        // Use CustomFilter.newBuilder() to construct.
        private CustomFilter(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.unknownFields = builder.getUnknownFields();
        }
        private CustomFilter(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

        private static final CustomFilter defaultInstance;
        public static CustomFilter getDefaultInstance() {
            return defaultInstance;
        }

        public CustomFilter getDefaultInstanceForType() {
            return defaultInstance;
        }

        private final com.google.protobuf.UnknownFieldSet unknownFields;
        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }
        private CustomFilter(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            initFields();
            int mutable_bitField0_ = 0;
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        default: {
                            if (!parseUnknownField(input, unknownFields,
                                    extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                        case 10: {
                            bitField0_ |= 0x00000001;
                            value_ = input.readBytes();
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e.getMessage()).setUnfinishedMessage(this);
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return filters.generated.FilterProtos.internal_static_CustomFilter_descriptor;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return filters.generated.FilterProtos.internal_static_CustomFilter_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            filters.generated.FilterProtos.CustomFilter.class, filters.generated.FilterProtos.CustomFilter.Builder.class);
        }

        public static com.google.protobuf.Parser<CustomFilter> PARSER =
                new com.google.protobuf.AbstractParser<CustomFilter>() {
                    public CustomFilter parsePartialFrom(
                            com.google.protobuf.CodedInputStream input,
                            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                            throws com.google.protobuf.InvalidProtocolBufferException {
                        return new CustomFilter(input, extensionRegistry);
                    }
                };

        @java.lang.Override
        public com.google.protobuf.Parser<CustomFilter> getParserForType() {
            return PARSER;
        }

        private int bitField0_;
        public static final int VALUE_FIELD_NUMBER = 1;
        private com.google.protobuf.ByteString value_;
        /**
         * <code>required bytes value = 1;</code>
         */
        public boolean hasValue() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }
        /**
         * <code>required bytes value = 1;</code>
         */
        public com.google.protobuf.ByteString getValue() {
            return value_;
        }

        private void initFields() {
            value_ = com.google.protobuf.ByteString.EMPTY;
        }
        private byte memoizedIsInitialized = -1;
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            if (!hasValue()) {
                memoizedIsInitialized = 0;
                return false;
            }
            memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            getSerializedSize();
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                output.writeBytes(1, value_);
            }
            getUnknownFields().writeTo(output);
        }

        private int memoizedSerializedSize = -1;
        public int getSerializedSize() {
            int size = memoizedSerializedSize;
            if (size != -1) return size;

            size = 0;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeBytesSize(1, value_);
            }
            size += getUnknownFields().getSerializedSize();
            memoizedSerializedSize = size;
            return size;
        }

        private static final long serialVersionUID = 0L;
        @java.lang.Override
        protected java.lang.Object writeReplace()
                throws java.io.ObjectStreamException {
            return super.writeReplace();
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof filters.generated.FilterProtos.CustomFilter)) {
                return super.equals(obj);
            }
            filters.generated.FilterProtos.CustomFilter other = (filters.generated.FilterProtos.CustomFilter) obj;

            boolean result = true;
            result = result && (hasValue() == other.hasValue());
            if (hasValue()) {
                result = result && getValue()
                        .equals(other.getValue());
            }
            result = result &&
                    getUnknownFields().equals(other.getUnknownFields());
            return result;
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptorForType().hashCode();
            if (hasValue()) {
                hash = (37 * hash) + VALUE_FIELD_NUMBER;
                hash = (53 * hash) + getValue().hashCode();
            }
            hash = (29 * hash) + getUnknownFields().hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static filters.generated.FilterProtos.CustomFilter parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static filters.generated.FilterProtos.CustomFilter parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static filters.generated.FilterProtos.CustomFilter parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static filters.generated.FilterProtos.CustomFilter parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static filters.generated.FilterProtos.CustomFilter parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return PARSER.parseFrom(input);
        }
        public static filters.generated.FilterProtos.CustomFilter parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }
        public static filters.generated.FilterProtos.CustomFilter parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return PARSER.parseDelimitedFrom(input);
        }
        public static filters.generated.FilterProtos.CustomFilter parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }
        public static filters.generated.FilterProtos.CustomFilter parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return PARSER.parseFrom(input);
        }
        public static filters.generated.FilterProtos.CustomFilter parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() { return Builder.create(); }
        public Builder newBuilderForType() { return newBuilder(); }
        public static Builder newBuilder(filters.generated.FilterProtos.CustomFilter prototype) {
            return newBuilder().mergeFrom(prototype);
        }
        public Builder toBuilder() { return newBuilder(this); }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }
        /**
         * Protobuf type {@code CustomFilter}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessage.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:CustomFilter)
                filters.generated.FilterProtos.CustomFilterOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return filters.generated.FilterProtos.internal_static_CustomFilter_descriptor;
            }

            protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return filters.generated.FilterProtos.internal_static_CustomFilter_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                filters.generated.FilterProtos.CustomFilter.class, filters.generated.FilterProtos.CustomFilter.Builder.class);
            }

            // Construct using filters.filters.generated.FilterProtos.CustomFilter.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessage.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }
            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                }
            }
            private static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                super.clear();
                value_ = com.google.protobuf.ByteString.EMPTY;
                bitField0_ = (bitField0_ & ~0x00000001);
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return filters.generated.FilterProtos.internal_static_CustomFilter_descriptor;
            }

            public filters.generated.FilterProtos.CustomFilter getDefaultInstanceForType() {
                return filters.generated.FilterProtos.CustomFilter.getDefaultInstance();
            }

            public filters.generated.FilterProtos.CustomFilter build() {
                filters.generated.FilterProtos.CustomFilter result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public filters.generated.FilterProtos.CustomFilter buildPartial() {
                filters.generated.FilterProtos.CustomFilter result = new filters.generated.FilterProtos.CustomFilter(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                    to_bitField0_ |= 0x00000001;
                }
                result.value_ = value_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof filters.generated.FilterProtos.CustomFilter) {
                    return mergeFrom((filters.generated.FilterProtos.CustomFilter)other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(filters.generated.FilterProtos.CustomFilter other) {
                if (other == filters.generated.FilterProtos.CustomFilter.getDefaultInstance()) return this;
                if (other.hasValue()) {
                    setValue(other.getValue());
                }
                this.mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (!hasValue()) {

                    return false;
                }
                return true;
            }

            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                filters.generated.FilterProtos.CustomFilter parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (filters.generated.FilterProtos.CustomFilter) e.getUnfinishedMessage();
                    throw e;
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }
            private int bitField0_;

            private com.google.protobuf.ByteString value_ = com.google.protobuf.ByteString.EMPTY;
            /**
             * <code>required bytes value = 1;</code>
             */
            public boolean hasValue() {
                return ((bitField0_ & 0x00000001) == 0x00000001);
            }
            /**
             * <code>required bytes value = 1;</code>
             */
            public com.google.protobuf.ByteString getValue() {
                return value_;
            }
            /**
             * <code>required bytes value = 1;</code>
             */
            public Builder setValue(com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                bitField0_ |= 0x00000001;
                value_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>required bytes value = 1;</code>
             */
            public Builder clearValue() {
                bitField0_ = (bitField0_ & ~0x00000001);
                value_ = getDefaultInstance().getValue();
                onChanged();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:CustomFilter)
        }

        static {
            defaultInstance = new CustomFilter(true);
            defaultInstance.initFields();
        }

        // @@protoc_insertion_point(class_scope:CustomFilter)
    }

    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_CustomFilter_descriptor;
    private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
            internal_static_CustomFilter_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }
    private static com.google.protobuf.Descriptors.FileDescriptor
            descriptor;
    static {
        java.lang.String[] descriptorData = {
                "\n\023CustomFilters.proto\"\035\n\014CustomFilter\022\r\n" +
                        "\005value\030\001 \002(\014B)\n\021filters.generatedB\014Filte" +
                        "rProtosH\001\210\001\001\240\001\001"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[] {
                        }, assigner);
        internal_static_CustomFilter_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_CustomFilter_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                internal_static_CustomFilter_descriptor,
                new java.lang.String[] { "Value", });
    }

    // @@protoc_insertion_point(outer_class_scope)
}